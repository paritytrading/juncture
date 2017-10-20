package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.juncture.cboe.fx.itch.ITCH.*;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * The interface for inbound events on the client side.
 */
public interface ITCHClientListener {

    /**
     * Receive an indication of a heartbeat timeout.
     *
     * @param session the session
     * @throws IOException if an I/O error occurs
     */
    void heartbeatTimeout(ITCHClient session) throws IOException;

    /**
     * Receive a Login Accepted packet (1.2.1).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void loginAccepted(ITCHClient session, LoginAccepted packet) throws IOException;

    /**
     * Receive a Login Rejected packet (1.2.2).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void loginRejected(ITCHClient session, LoginRejected packet) throws IOException;

    /**
     * Receive a Sequenced Data packet (1.2.3).
     *
     * @param session the session
     * @param header the header
     * @param payload the payload
     * @throws IOException if an I/O error occurs
     */
    void sequencedData(ITCHClient session, SequencedData header, ByteBuffer payload) throws IOException;

    /**
     * Receive an indication of the end of session (1.2.5).
     *
     * @param session the session
     * @throws IOException if an I/O error occurs
     */
    void endOfSession(ITCHClient session) throws IOException;

    /**
     * Receive an Error Notification packet (1.2.6).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void errorNotification(ITCHClient session, ErrorNotification packet) throws IOException;

    /**
     * Receive an Instrument Directory packet (1.2.7).
     *
     * @param session the session
     * @param packet the packet
     * @throws IOException if an I/O error occurs
     */
    void instrumentDirectory(ITCHClient session, InstrumentDirectory packet) throws IOException;

}
