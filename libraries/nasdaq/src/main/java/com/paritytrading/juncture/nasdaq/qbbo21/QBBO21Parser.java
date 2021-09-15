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

import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.IPOQuotingPeriodUpdate;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_ADD_ORDER;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_CROSS_TRADE;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_MWCB_DECLINE_LEVEL;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_MWCB_STATUS;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_OPERATIONAL_HALT;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_REG_SHO_RESTRICTION;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_RPII;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_STOCK_DIRECTORY;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_STOCK_TRADING_ACTION;
import static com.paritytrading.juncture.nasdaq.qbbo21.QBBO21.MESSAGE_TYPE_SYSTEM_EVENT;
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

import com.paritytrading.nassau.MessageListener;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A parser for inbound messages.
 */
public class QBBO21Parser implements MessageListener {

    private SystemEvent               systemEvent;
    private StockDirectory            stockDirectory;
    private StockTradingAction        stockTradingAction;
    private RegSHORestriction         regSHORestriction;
    private MWCBDeclineLevel          mwcbDeclineLevel;
    private MWCBStatus                mwcbStatus;
    private IPOQuotingPeriodUpdate    ipoQuotingPeriodUpdate;
    private OperationalHalt           operationalHalt;
    private NextSharesQuotation nextSharesQuotation;
    private Quotation quotation;
    private RPII                      rpii;

    private QBBO21Listener listener;

    /**
     * Create a parser for inbound messages.
     *
     * @param listener the message listener
     */
    public QBBO21Parser(QBBO21Listener listener) {
        this.systemEvent               = new SystemEvent();
        this.stockDirectory            = new StockDirectory();
        this.stockTradingAction        = new StockTradingAction();
        this.regSHORestriction         = new RegSHORestriction();
        this.mwcbDeclineLevel          = new MWCBDeclineLevel();
        this.mwcbStatus                = new MWCBStatus();
        this.ipoQuotingPeriodUpdate    = new IPOQuotingPeriodUpdate();
        this.operationalHalt           = new OperationalHalt();
        this.nextSharesQuotation = new NextSharesQuotation();
        this.quotation = new Quotation();
        this.rpii                      = new RPII();

        this.listener = listener;
    }

    @Override
    public void message(ByteBuffer buffer) throws IOException {
        byte messageType = buffer.get();

        switch (messageType) {
        case MESSAGE_TYPE_SYSTEM_EVENT:
            systemEvent.get(buffer);
            listener.systemEvent(systemEvent);
            break;
        case MESSAGE_TYPE_STOCK_DIRECTORY:
            stockDirectory.get(buffer);
            listener.stockDirectory(stockDirectory);
            break;
        case MESSAGE_TYPE_STOCK_TRADING_ACTION:
            stockTradingAction.get(buffer);
            listener.stockTradingAction(stockTradingAction);
            break;
        case MESSAGE_TYPE_REG_SHO_RESTRICTION:
            regSHORestriction.get(buffer);
            listener.regSHORestriction(regSHORestriction);
            break;
        case MESSAGE_TYPE_MWCB_DECLINE_LEVEL:
            mwcbDeclineLevel.get(buffer);
            listener.mwcbDeclineLevel(mwcbDeclineLevel);
            break;
        case MESSAGE_TYPE_MWCB_STATUS:
            mwcbStatus.get(buffer);
            listener.mwcbStatus(mwcbStatus);
            break;
        case MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE:
            ipoQuotingPeriodUpdate.get(buffer);
            listener.ipoQuotingPeriodUpdate(ipoQuotingPeriodUpdate);
            break;
        case MESSAGE_TYPE_OPERATIONAL_HALT:
            operationalHalt.get(buffer);
            listener.operationalHalt(operationalHalt);
            break;
        case MESSAGE_TYPE_ADD_ORDER:
            nextSharesQuotation.get(buffer);
            listener.nextSharesQuotation(nextSharesQuotation);
            break;
        case MESSAGE_TYPE_CROSS_TRADE:
            quotation.get(buffer);
            listener.quotation(quotation);
            break;
        case MESSAGE_TYPE_RPII:
            rpii.get(buffer);
            listener.rpii(rpii);
            break;
        default:
            throw new QBBO21Exception("Unknown message type: " + (char)messageType);
        }
    }

}
