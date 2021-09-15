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
package com.paritytrading.juncture.nasdaq.qbbo21;

import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MWCBDeclineLevel;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MWCBStatus;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.NextSharesQuotation;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.OperationalHalt;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.Quotation;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.RPII;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.RegSHORestriction;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.StockDirectory;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.StockTradingAction;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.SystemEvent;

import java.io.IOException;
import com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.IPOQuotingPeriodUpdate;

/**
 * The interface for inbound messages.
 */
public interface QBBO21Listener {

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
     * Receive an Operational Halt message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void operationalHalt(OperationalHalt message) throws IOException;

    /**
     * Receive an Add Order message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void nextSharesQuotation(NextSharesQuotation message) throws IOException;

    /**
     * Receive a Cross Trade message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void quotation(Quotation message) throws IOException;

    /**
     * Receive an RPII message.
     *
     * @param message the message
     * @throws IOException if an I/O error occurs
     */
    void rpii(RPII message) throws IOException;

}
