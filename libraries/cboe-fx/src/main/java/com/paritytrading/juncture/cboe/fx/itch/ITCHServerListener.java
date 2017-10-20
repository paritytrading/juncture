package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.juncture.cboe.fx.itch.ITCH.*;

import java.io.IOException;

/**
 * The interface for inbound events on the server side.
 */
public interface ITCHServerListener {

    /**
     * Receive an indication of a heartbeat timeout.
     *
     * @param session the session
     * @throws IOException if an I/O error occurs
     */
    void heartbeatTimeout(ITCHServer session) throws IOException;

    /**
     * Receive a Login Request packet (1.3.1).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void loginRequest(ITCHServer session, LoginRequest packet) throws IOException;

    /**
     * Receive a LogoutRequest packet (1.3.2).
     *
     * @param session the session
     * @throws IOException if an I/O error occurs
     */
    void logoutRequest(ITCHServer session) throws IOException;

    /**
     * Receive a Market Snapshot Request packet (1.3.4).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void marketSnapshotRequest(ITCHServer session, MarketSnapshotRequest packet) throws IOException;

    /**
     * Receive a Ticker Subscribe Request packet (1.3.5).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void tickerSubscribeRequest(ITCHServer session, TickerSubscribeRequest packet) throws IOException;

    /**
     * Receive an Ticker Unsubscribe Request packet (1.3.6).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void tickerUnsubscribeRequest(ITCHServer session, TickerUnsubscribeRequest packet) throws IOException;

    /**
     * Receive a Market Data Subscribe Request packet (1.3.7).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void marketDataSubscribeRequest(ITCHServer session, MarketDataSubscribeRequest packet) throws IOException;

    /**
     * Receive a Market Data Unsubscribe Request packet (1.3.8).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void marketDataUnsubscribeRequest(ITCHServer session, MarketDataUnsubscribeRequest packet) throws IOException;

    /**
     * Receive an Instrument Directory Request packet (1.3.9).
     *
     * @param session the session
     * @throws IOException if an I/O error occurs
     */
    void instrumentDirectoryRequest(ITCHServer session) throws IOException;

}
