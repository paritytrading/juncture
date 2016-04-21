package com.paritytrading.juncture.hotspot.itch;

import static com.paritytrading.juncture.hotspot.itch.ITCH.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * An implementation of the client side of ITCH Session Management Protocol.
 */
public class ITCHClient extends ITCHSession {

    private LoginAccepted       loginAccepted;
    private LoginRejected       loginRejected;
    private SequencedData       sequencedData;
    private ErrorNotification   errorNotification;
    private InstrumentDirectory instrumentDirectory;

    private ByteBuffer txPayload;

    private ITCHClientListener listener;

    /**
     * Create a client. The underlying socket channel can be either blocking
     * or non-blocking.
     *
     * @param channel the underlying socket channel
     * @param rxBufferCapacity the receive buffer capacity
     * @param listener the inbound packet listener
     */
    public ITCHClient(SocketChannel channel, int rxBufferCapacity,
            ITCHClientListener listener) {
        this(SystemClock.INSTANCE, channel, rxBufferCapacity, listener);
    }

    ITCHClient(Clock clock, SocketChannel channel, int rxBufferCapacity,
            ITCHClientListener listener) {
        super(clock, channel, rxBufferCapacity, MESSAGE_TYPE_CLIENT_HEARTBEAT);

        this.loginAccepted       = new LoginAccepted();
        this.loginRejected       = new LoginRejected();
        this.sequencedData       = new SequencedData();
        this.errorNotification   = new ErrorNotification();
        this.instrumentDirectory = new InstrumentDirectory();

        this.txPayload = ByteBuffer.allocate(90);

        this.listener = listener;
    }

    /**
     * Send a Login Request packet (1.3.1).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void login(LoginRequest packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_LOGIN_REQUEST, txPayload);
    }

    /**
     * Send a Logout Request packet (1.3.2).
     *
     * @throws IOException if an I/O error occurs
     */
    public void logout() throws IOException {
        send(MESSAGE_TYPE_LOGOUT_REQUEST);
    }

    /**
     * Send a Market Snapshot Request packet (1.3.4).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void request(MarketSnapshotRequest packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_MARKET_SNAPSHOT_REQUEST, txPayload);
    }

    /**
     * Send a Ticker Subscribe Request packet (1.3.5).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void request(TickerSubscribeRequest packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_TICKER_SUBSCRIBE_REQUEST, txPayload);
    }

    /**
     * Send a Ticker Unsubscribe Request packet (1.3.6).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void request(TickerUnsubscribeRequest packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_TICKER_UNSUBSCRIBE_REQUEST, txPayload);
    }

    /**
     * Send a Market Data Subscribe Request packet (1.3.7).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void request(MarketDataSubscribeRequest packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_MARKET_DATA_SUBSCRIBE_REQUEST, txPayload);
    }

    /**
     * Send a Market Data Unsubscribe Request packet (1.3.8).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void request(MarketDataUnsubscribeRequest packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_MARKET_DATA_UNSUBSCRIBE_REQUEST, txPayload);
    }

    /**
     * Send an Instrument Directory Request packet (1.3.9).
     *
     * @throws IOException if an I/O error occurs
     */
    public void requestInstrumentDirectory() throws IOException {
        send(MESSAGE_TYPE_INSTRUMENT_DIRECTORY_REQUEST);
    }

    @Override
    protected void heartbeatTimeout() throws IOException {
        listener.heartbeatTimeout(this);
    }

    @Override
    protected void packet(byte messageType, ByteBuffer packet) throws IOException {
        switch (messageType) {
        case MESSAGE_TYPE_LOGIN_ACCEPTED:
            loginAccepted.get(packet);
            listener.loginAccepted(this, loginAccepted);
            break;
        case MESSAGE_TYPE_LOGIN_REJECTED:
            loginRejected.get(packet);
            listener.loginRejected(this, loginRejected);
            break;
        case MESSAGE_TYPE_SEQUENCED_DATA:
            if (packet.hasRemaining()) {
                sequencedData.get(packet);
                listener.sequencedData(this, sequencedData, packet);
            } else {
                listener.endOfSession(this);
            }
            break;
        case MESSAGE_TYPE_SERVER_HEARTBEAT:
            break;
        case MESSAGE_TYPE_ERROR_NOTIFICATION:
            errorNotification.get(packet);
            listener.errorNotification(this, errorNotification);
            break;
        case MESSAGE_TYPE_INSTRUMENT_DIRECTORY:
            instrumentDirectory.get(packet);
            listener.instrumentDirectory(this, instrumentDirectory);
            break;
        }
    }

}
