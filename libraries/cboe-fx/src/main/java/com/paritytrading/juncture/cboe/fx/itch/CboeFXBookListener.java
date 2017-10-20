package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.juncture.cboe.fx.itch.CboeFXBook.*;

import java.io.IOException;

/**
 * The interface for inbound Cboe FX Book Protocol messages.
 */
public interface CboeFXBookListener {

    /**
     * Receive a New Order message (2.2.1).
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void newOrder(NewOrder message) throws IOException;

    /**
     * Receive a Modify Order message (2.2.2).
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void modifyOrder(ModifyOrder message) throws IOException;

    /**
     * Receive a Cancel Order message (2.2.3).
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void cancelOrder(CancelOrder message) throws IOException;

    /**
     * Receive an indication of the start of a Market Snapshot message
     * (2.2.4).
     *
     * @throws IOException if an I/O error occurs
     */
    void marketSnapshotStart() throws IOException;

    /**
     * Receive an indication of an entry in a  Market Snapshot message
     * (2.2.4).
     *
     * @param entry the entry
     * @throws IOException if an I/O error occurs
     */
    void marketSnapshotEntry(MarketSnapshotEntry entry) throws IOException;

    /**
     * Receive an indication of the end of a Market Snapshot message (2.2.4).
     *
     * @throws IOException if an I/O error occurs
     */
    void marketSnapshotEnd() throws IOException;

    /**
     * Receive a Ticker message (2.2.5).
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void ticker(Ticker message) throws IOException;

}
