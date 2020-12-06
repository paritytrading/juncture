/*
 * Copyright 2015 Juncture authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.juncture.cboe.fx.itch.ITCH.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * An implementation of the server side of ITCH Session Management Protocol.
 */
public class ITCHServer extends ITCHSession {

    private LoginRequest                 loginRequest;
    private MarketSnapshotRequest        marketSnapshotRequest;
    private TickerSubscribeRequest       tickerSubscribeRequest;
    private TickerUnsubscribeRequest     tickerUnsubscribeRequest;
    private MarketDataSubscribeRequest   marketDataSubscribeRequest;
    private MarketDataUnsubscribeRequest marketDataUnsubscribeRequest;

    private ByteBuffer txPayload;

    private ITCHServerListener listener;

    /**
     * Create a server. The underlying socket channel can be either blocking
     * or non-blocking.
     *
     * @param channel the underlying socket channel
     * @param rxBufferCapacity the receive buffer capacity
     * @param listener the inbound packet listener
     */
    public ITCHServer(SocketChannel channel, int rxBufferCapacity,
            ITCHServerListener listener) {
        this(SystemClock.INSTANCE, channel, rxBufferCapacity, listener);
    }

    ITCHServer(Clock clock, SocketChannel channel, int rxBufferCapacity,
            ITCHServerListener listener) {
        super(clock, channel, rxBufferCapacity, MESSAGE_TYPE_SERVER_HEARTBEAT);

        this.loginRequest                 = new LoginRequest();
        this.marketSnapshotRequest        = new MarketSnapshotRequest();
        this.tickerSubscribeRequest       = new TickerSubscribeRequest();
        this.tickerUnsubscribeRequest     = new TickerUnsubscribeRequest();
        this.marketDataSubscribeRequest   = new MarketDataSubscribeRequest();
        this.marketDataUnsubscribeRequest = new MarketDataUnsubscribeRequest();

        this.txPayload = ByteBuffer.allocateDirect(8192);

        this.listener = listener;
    }

    /**
     * Send a Login Accepted packet (1.2.1).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void accept(LoginAccepted packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_LOGIN_ACCEPTED, txPayload);
    }

    /**
     * Send a Login Rejected packet (1.2.2).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void reject(LoginRejected packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_LOGIN_REJECTED, txPayload);
    }

    /**
     * Send a Sequenced Data packet (1.2.3).
     *
     * @param packet the packet
     * @param payload the payload
     * @throws IOException if an I/O error occurs
     */
    public void send(SequencedData packet, ByteBuffer payload) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_SEQUENCED_DATA, txPayload, payload);
    }

    /**
     * Send an indication of the end of session (1.2.5).
     *
     * @throws IOException if an I/O error occurs
     */
    public void endSession() throws IOException {
        send(MESSAGE_TYPE_SEQUENCED_DATA);
    }

    /**
     * Send an Error Notification packet (1.2.6).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     *
     */
    public void notifyError(ErrorNotification packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_ERROR_NOTIFICATION, txPayload);
    }

    /**
     * Send an Instrument Directory packet (1.2.7).
     *
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    public void instrumentDirectory(InstrumentDirectory packet) throws IOException {
        txPayload.clear();
        packet.put(txPayload);
        txPayload.flip();

        send(MESSAGE_TYPE_INSTRUMENT_DIRECTORY, txPayload);
    }

    @Override
    protected void heartbeatTimeout() throws IOException {
        listener.heartbeatTimeout(this);
    }

    @Override
    protected void packet(byte messageType, ByteBuffer packet) throws IOException {
        switch (messageType) {
        case MESSAGE_TYPE_LOGIN_REQUEST:
            loginRequest.get(packet);
            listener.loginRequest(this, loginRequest);
            break;
        case MESSAGE_TYPE_LOGOUT_REQUEST:
            listener.logoutRequest(this);
            break;
        case MESSAGE_TYPE_CLIENT_HEARTBEAT:
            break;
        case MESSAGE_TYPE_MARKET_SNAPSHOT_REQUEST:
            marketSnapshotRequest.get(packet);
            listener.marketSnapshotRequest(this, marketSnapshotRequest);
            break;
        case MESSAGE_TYPE_TICKER_SUBSCRIBE_REQUEST:
            tickerSubscribeRequest.get(packet);
            listener.tickerSubscribeRequest(this, tickerSubscribeRequest);
            break;
        case MESSAGE_TYPE_TICKER_UNSUBSCRIBE_REQUEST:
            tickerUnsubscribeRequest.get(packet);
            listener.tickerUnsubscribeRequest(this, tickerUnsubscribeRequest);
            break;
        case MESSAGE_TYPE_MARKET_DATA_SUBSCRIBE_REQUEST:
            marketDataSubscribeRequest.get(packet);
            listener.marketDataSubscribeRequest(this, marketDataSubscribeRequest);
            break;
        case MESSAGE_TYPE_MARKET_DATA_UNSUBSCRIBE_REQUEST:
            marketDataUnsubscribeRequest.get(packet);
            listener.marketDataUnsubscribeRequest(this, marketDataUnsubscribeRequest);
            break;
        case MESSAGE_TYPE_INSTRUMENT_DIRECTORY_REQUEST:
            listener.instrumentDirectoryRequest(this);
        }
    }

}
