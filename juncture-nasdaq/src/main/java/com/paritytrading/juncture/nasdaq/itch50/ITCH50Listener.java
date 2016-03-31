package com.paritytrading.juncture.nasdaq.itch50;

import static com.paritytrading.juncture.nasdaq.itch50.ITCH50.*;

import java.io.IOException;

/**
 * The interface for inbound messages.
 */
public interface ITCH50Listener {

    /**
     * Receive a System Event message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void systemEvent(SystemEvent message) throws IOException;

    /**
     * Receive a Stock Directory message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void stockDirectory(StockDirectory message) throws IOException;

    /**
     * Receive a Stock Trading Action message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void stockTradingAction(StockTradingAction message) throws IOException;

    /**
     * Receive a Reg SHO Restriction message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void regSHORestriction(RegSHORestriction message) throws IOException;

    /**
     * Receive a Market Participant Position message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void marketParticipantPosition(MarketParticipantPosition message) throws IOException;

    /**
     * Receive an MWCB Decline Level message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void mwcbDeclineLevel(MWCBDeclineLevel message) throws IOException;

    /**
     * Receive an MWCB Status message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void mwcbStatus(MWCBStatus message) throws IOException;

    /**
     * Receive an IPO Quoting Period Update message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void ipoQuotingPeriodUpdate(IPOQuotingPeriodUpdate message) throws IOException;

    /**
     * Receive an Add Order message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void addOrder(AddOrder message) throws IOException;

    /**
     * Receive an Add Order with MPID message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void addOrderMPID(AddOrderMPID message) throws IOException;

    /**
     * Receive an Order Executed message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void orderExecuted(OrderExecuted message) throws IOException;

    /**
     * Receive an Order Executed With Price message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void orderExecutedWithPrice(OrderExecutedWithPrice message) throws IOException;

    /**
     * Receive an Order Cancel message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void orderCancel(OrderCancel message) throws IOException;

    /**
     * Receive an Order Delete message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void orderDelete(OrderDelete message) throws IOException;

    /**
     * Receive an Order Replace message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void orderReplace(OrderReplace message) throws IOException;

    /**
     * Receive a Trade message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void trade(Trade message) throws IOException;

    /**
     * Receive a Cross Trade message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void crossTrade(CrossTrade message) throws IOException;

    /**
     * Receive a Broken Trade message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void brokenTrade(BrokenTrade message) throws IOException;

    /**
     * Receive a NOII message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void noii(NOII message) throws IOException;

    /**
     * Receive an RPII message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void rpii(RPII message) throws IOException;

}
