package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.juncture.cboe.fx.itch.ITCH.*;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * The base for both the client and server side of ITCH Session Management
 * Protocol.
 */
public abstract class ITCHSession implements Closeable {

    private static final long RX_HEARTBEAT_TIMEOUT_MILLIS  = 15000;
    private static final long TX_HEARTBEAT_INTERVAL_MILLIS =  1000;

    private static final byte TLF = 0x0A;

    private Clock clock;

    private SocketChannel channel;

    /*
     * This variable is written on data reception and read on session
     * keep-alive. These two functions can run on different threads
     * without locking.
     */
    private volatile long lastRxMillis;

    /*
     * This variable is written on data transmission and read on session
     * keep-alive. These two functions can run on different threads but
     * require locking.
     */
    private long lastTxMillis;

    private ByteBuffer rxBuffer;

    private ByteBuffer txHeader;
    private ByteBuffer txTrailer;

    private ByteBuffer[] txBuffers;

    private byte heartbeatMessageType;

    protected ITCHSession(Clock clock, SocketChannel channel, int rxBufferCapacity,
            byte heartbeatMessageType) {
        this.clock   = clock;
        this.channel = channel;

        this.lastRxMillis = clock.currentTimeMillis();
        this.lastTxMillis = clock.currentTimeMillis();

        this.rxBuffer = ByteBuffer.allocateDirect(rxBufferCapacity);

        this.txHeader  = ByteBuffer.allocateDirect(1);
        this.txTrailer = ByteBuffer.allocateDirect(1);

        this.txTrailer.put(TLF);

        this.txBuffers = new ByteBuffer[4];

        this.txBuffers[0] = txHeader;
        this.txBuffers[3] = txTrailer;

        this.heartbeatMessageType = heartbeatMessageType;
    }

    /**
     * Get the underlying socket channel.
     *
     * @return the underlying socket channel
     */
    public SocketChannel getChannel() {
        return channel;
    }

    /**
     * Receive data from the underlying socket channel. For each packet
     * received, invoke the corresponding listener if applicable.
     *
     * @return The number of bytes read, possibly zero, or {@code -1} if the
     *   channel has reached end-of-stream
     * @throws IOException if an I/O error occurs
     */
    public int receive() throws IOException {
        int bytes = channel.read(rxBuffer);

        if (bytes <= 0)
            return bytes;

        rxBuffer.flip();

        while (parse());

        rxBuffer.compact();

        if (rxBuffer.position() == rxBuffer.capacity())
            throw new ITCHException("Packet length exceeds buffer capacity");

        receivedData();

        return bytes;
    }

    private boolean parse() throws IOException {
        if (rxBuffer.remaining() < 2)
            return false;

        rxBuffer.mark();

        byte messageType = rxBuffer.get();

        int trailerIndex = trailerIndex();
        if (trailerIndex < 0) {
            rxBuffer.reset();
            return false;
        }

        int packetLength = trailerIndex - rxBuffer.position();

        int limit = rxBuffer.limit();

        rxBuffer.limit(rxBuffer.position() + packetLength);

        int position = rxBuffer.limit() + 1;

        packet(messageType, rxBuffer);

        rxBuffer.limit(limit);
        rxBuffer.position(position);

        return true;
    }

    private int trailerIndex() {
        for (int i = rxBuffer.position(); i < rxBuffer.limit(); i++) {
            if (rxBuffer.get(i) == TLF)
                return i;
        }

        return -1;
    }

    /**
     * Keep the session alive.
     *
     * <p>If the heartbeat interval duration has passed since the last packet
     * was sent, send a Heartbeat packet. If the heartbeat timeout duration
     * has passed since the last packet was received, invoke the corresponding
     * method on the listener.</p>
     *
     * @throws IOException if an I/O error occurs
     */
    public void keepAlive() throws IOException {
        long currentTimeMillis = clock.currentTimeMillis();

        if (currentTimeMillis - lastTxMillis > TX_HEARTBEAT_INTERVAL_MILLIS)
            send(heartbeatMessageType);

        if (currentTimeMillis - lastRxMillis > RX_HEARTBEAT_TIMEOUT_MILLIS)
            handleHeartbeatTimeout();
    }

    /**
     * Close the underlying socket channel.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException {
        channel.close();
    }

    protected abstract void heartbeatTimeout() throws IOException;

    protected abstract void packet(byte messageType, ByteBuffer payload) throws IOException;

    protected void send(byte messageType) throws IOException {
        txHeader.clear();
        txHeader.put(messageType);
        txHeader.flip();

        txBuffers[1] = txTrailer;

        txTrailer.flip();

        int remaining = txHeader.remaining() + txTrailer.remaining();

        do {
            remaining -= channel.write(txBuffers, 0, 2);
        } while (remaining > 0);

        sentData();
    }

    protected void send(byte messageType, ByteBuffer payload) throws IOException {
        txHeader.clear();
        txHeader.put(messageType);
        txHeader.flip();

        txBuffers[1] = payload;
        txBuffers[2] = txTrailer;

        txTrailer.flip();

        int remaining = txHeader.remaining() + payload.remaining() + txTrailer.remaining();

        do {
            remaining -= channel.write(txBuffers, 0, 3);
        } while (remaining > 0);

        sentData();
    }

    protected void send(byte messageType, ByteBuffer payload1, ByteBuffer payload2) throws IOException {
        txHeader.clear();
        txHeader.put(messageType);
        txHeader.flip();

        txBuffers[1] = payload1;
        txBuffers[2] = payload2;

        txTrailer.flip();

        int remaining = txHeader.remaining() + payload1.remaining() + payload2.remaining()
                + txTrailer.remaining();

        do {
            remaining -= channel.write(txBuffers);
        } while (remaining > 0);

        sentData();
    }

    protected void unexpectedMessageType(byte messageType) throws ITCHException {
        throw new ITCHException("Unexpected message type: " + (char)messageType);
    }

    private void handleHeartbeatTimeout() throws IOException {
        heartbeatTimeout();

        receivedData();
    }

    private void receivedData() {
        lastRxMillis = clock.currentTimeMillis();
    }

    private void sentData() {
        lastTxMillis = clock.currentTimeMillis();
    }

}
