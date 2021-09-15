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

import static com.paritytrading.foundation.ByteBuffers.getUnsignedInt;
import static com.paritytrading.foundation.ByteBuffers.getUnsignedShort;
import static com.paritytrading.foundation.ByteBuffers.putUnsignedInt;
import static com.paritytrading.foundation.ByteBuffers.putUnsignedShort;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.StandardCharsets;

/**
 * Common definitions.
 */
public class QBBO21 {

    private QBBO21() {
    }

    public static final byte YES           = 'Y';
    public static final byte NO            = 'N';
    public static final byte NOT_AVAILABLE = ' ';

    public static final byte MESSAGE_TYPE_SYSTEM_EVENT                = 'S';
    public static final byte MESSAGE_TYPE_STOCK_DIRECTORY             = 'R';
    public static final byte MESSAGE_TYPE_STOCK_TRADING_ACTION        = 'H';
    public static final byte MESSAGE_TYPE_REG_SHO_RESTRICTION         = 'Y';
    public static final byte MESSAGE_TYPE_MWCB_DECLINE_LEVEL          = 'V';
    public static final byte MESSAGE_TYPE_MWCB_STATUS                 = 'W';
    public static final byte MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE   = 'K';
    public static final byte MESSAGE_TYPE_OPERATIONAL_HALT            = 'h';
    public static final byte MESSAGE_TYPE_ADD_ORDER                   = 'A';
    public static final byte MESSAGE_TYPE_CROSS_TRADE                 = 'Q';
    public static final byte MESSAGE_TYPE_RPII                        = 'N';

    /*
     * Event Code (4.1) values.
     */
    public static final byte EVENT_CODE_START_OF_MESSAGES     = 'O';
    public static final byte EVENT_CODE_START_OF_SYSTEM_HOURS = 'S';
    public static final byte EVENT_CODE_START_OF_MARKET_HOURS = 'Q';
    public static final byte EVENT_CODE_END_OF_MARKET_HOURS   = 'M';
    public static final byte EVENT_CODE_END_OF_SYSTEM_HOURS   = 'E';
    public static final byte EVENT_CODE_END_OF_MESSAGES       = 'C';

    /*
     * Financial Status Indicator (4.2.1) values.
     */
    public static final byte FINANCIAL_STATUS_INDICATOR_DEFICIENT                       = 'D';
    public static final byte FINANCIAL_STATUS_INDICATOR_DELINQUENT                      = 'E';
    public static final byte FINANCIAL_STATUS_INDICATOR_BANKRUPT                        = 'Q';
    public static final byte FINANCIAL_STATUS_INDICATOR_SUSPENDED                       = 'S';
    public static final byte FINANCIAL_STATUS_INDICATOR_DEFICIENT_BANKRUPT              = 'G';
    public static final byte FINANCIAL_STATUS_INDICATOR_DEFICIENT_DELINQUENT            = 'H';
    public static final byte FINANCIAL_STATUS_INDICATOR_DELINQUENT_BANKRUPT             = 'J';
    public static final byte FINANCIAL_STATUS_INDICATOR_DEFICIENT_DELINQUENT_BANKRUPT   = 'K';
    public static final byte FINANCIAL_STATUS_INDICATOR_CREATIONS_REDEMPTIONS_SUSPENDED = 'C';
    public static final byte FINANCIAL_STATUS_INDICATOR_NORMAL                          = 'N';
    public static final byte FINANCIAL_STATUS_INDICATOR_NOT_AVAILABLE                   = ' ';

    /*
     * Authenticity (4.2.1) values.
     */
    public static final byte AUTHENTICITY_PRODUCTION = 'P';
    public static final byte AUTHENTICTY_TEST        = 'T';

    /*
     * LULD Reference Price Tier (4.2.1) values.
     */
    public static final byte LULD_REFERENCE_PRICE_TIER_1 = '1';
    public static final byte LULD_REFERENCE_PRICE_TIER_2 = '2';

    /*
     * Trading State (4.2.2) values.
     */
    public static final byte TRADING_STATE_HALTED           = 'H';
    public static final byte TRADING_STATE_PAUSED           = 'P';
    public static final byte TRADING_STATE_QUOTATION_PERIOD = 'Q';
    public static final byte TRADING_STATE_TRADING          = 'T';

    /*
     * Reg SHO Action (4.2.3) values.
     */
    public static final byte REG_SHO_ACTION_NO_PRICE_TEST                              = '0';
    public static final byte REG_SHO_ACTION_SHORT_SALE_PRICE_TEST_INTRA_DAY_PRICE_DROP = '1';
    public static final byte REG_SHO_ACTION_SHORT_SALE_PRICE_TEST                      = '2';

    /*
     * Breached Level (4.2.5.2) values.
     */
    public static final byte BREACHED_LEVEL_1 = '1';
    public static final byte BREACHED_LEVEL_2 = '2';
    public static final byte BREACHED_LEVEL_3 = '3';

    /*
     * IPO Quotation Release Qualifier (4.2.6) values.
     */
    public static final byte IPO_QUOTATION_RELEASE_QUALIFIER_ANTICIPATED_QUOTATION_TIME     = 'A';
    public static final byte IPO_QUOTATION_RELEASE_QUALIFIER_IPO_RELEASE_CANCELED_POSTPONED = 'C';

    /*
     * Market Code (4.2.8) values.
     */
    public static final byte MARKET_CODE_NASDAQ = 'Q';
    public static final byte MARKET_CODE_BX     = 'B';
    public static final byte MARKET_CODE_PSX    = 'X';

    /*
     * Operational Halt Action (4.2.8) values.
     */
    public static final byte OPERATIONAL_HALT_ACTION_OPERATIONALLY_HALTED = 'H';
    public static final byte OPERATIONAL_HALT_ACTION_TRADING_RESUMED      = 'T';

    /*
     * Interest Flag (4.7) values.
     */
    public static final byte INTEREST_FLAG_BUY  = 'B';
    public static final byte INTEREST_FLAG_SELL = 'S';
    public static final byte INTEREST_FLAG_BOTH = 'A';
    public static final byte INTEREST_FLAG_NONE = 'N';

    /**
     * A message.
     */
    public interface Message {

        /**
         * Read this message from the buffer.
         *
         * @param buffer a buffer
         * @throws BufferUnderflowException if there are fewer bytes remaining
         *   in the buffer than what this message consists of
         */
        void get(ByteBuffer buffer);

        /**
         * Write this message to the buffer.
         *
         * @param buffer a buffer
         * @throws BufferOverflowException if there are fewer bytes remaining
         *   in the buffer than what this message consists of
         * @throws ReadOnlyBufferException if the buffer is read-only
         */
        void put(ByteBuffer buffer);

        /**
         * Read 4 byte double
         *
         * @param buffer a buffer
         * @return double
         */
        default double readDouble4(ByteBuffer buffer) {
            return buffer.getInt() / 10000.0;
        }

        /**
         * Read 8 byte price
         *
         * @param buffer a buffer
         * @return price
         */
        default double readPrice8(ByteBuffer buffer) {
            return buffer.getLong() / 10000.0 / 1000;
        }

        /**
         * Read string
         *
         * @param buffer a buffer
         * @param length length
         * @return String
         */
        default String readString(ByteBuffer buffer, int length) {
            byte[] bytes = new byte[length];
            buffer.get(bytes);
            return new String(bytes, StandardCharsets.UTF_8).trim();
        }

        /**
         * Write double
         *
         * @param buffer a buffer
         * @param value  double value
         * @return Byte array
         */
        default void put(ByteBuffer buffer, double value) {
            buffer.putInt((int) (value * 10000));
        }

        /**
         * Write double
         *
         * @param buffer a buffer
         * @param value  double value
         * @return Byte array
         */
        default void putPrice8(ByteBuffer buffer, double value) {
            buffer.putLong((int) (value * 10000 * 1000));
        }

    }

    /**
     * A System Event message.
     */
    public static class SystemEvent implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public char eventCode;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            eventCode      = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_SYSTEM_EVENT);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put((byte) eventCode);
        }
    }

    /**
     * A Stock Directory (4.2.1) message.
     */
    public static class StockDirectory implements Message {
        public int   trackingNumber;
        public int   timestampHigh;
        public long  timestampLow;
        public String  stock;
        public char  marketCategory;
        public char  financialStatusIndicator;
        public long  roundLotSize;
        public char  roundLotsOnly;
        public char  issueClassification;
        public String issueSubType;
        public char  authenticity;
        public char  shortSaleThresholdIndicator;
        public char  ipoFlag;
        public char  luldReferencePriceTier;
        public char  etpFlag;
        public long  etpLeverageFactor;
        public char  inverseIndicator;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber              = getUnsignedShort(buffer);
            timestampHigh               = getUnsignedShort(buffer);
            timestampLow                = getUnsignedInt(buffer);
            stock                       = readString(buffer, 8);
            marketCategory              = (char) buffer.get();
            financialStatusIndicator    = (char) buffer.get();
            roundLotSize                = getUnsignedInt(buffer);
            roundLotsOnly               = (char) buffer.get();
            issueClassification         = (char) buffer.get();
            issueSubType                = readString(buffer, 2);
            authenticity                = (char) buffer.get();
            shortSaleThresholdIndicator = (char) buffer.get();
            ipoFlag                     = (char) buffer.get();
            luldReferencePriceTier      = (char) buffer.get();
            etpFlag                     = (char) buffer.get();
            etpLeverageFactor           = getUnsignedInt(buffer);
            inverseIndicator            = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_STOCK_DIRECTORY);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) marketCategory);
            buffer.put((byte) financialStatusIndicator);
            putUnsignedInt(buffer, roundLotSize);
            buffer.put((byte) roundLotsOnly);
            buffer.put((byte) issueClassification);
            buffer.put(issueSubType.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) authenticity);
            buffer.put((byte) shortSaleThresholdIndicator);
            buffer.put((byte) ipoFlag);
            buffer.put((byte) luldReferencePriceTier);
            buffer.put((byte) etpFlag);
            putUnsignedInt(buffer, etpLeverageFactor);
            buffer.put((byte) inverseIndicator);
        }
    }

    /**
     * A Stock Trading Action (4.2.2) message.
     */
    public static class StockTradingAction implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char securityClass;
        public char tradingState;
        public String  reason;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            securityClass  = (char) buffer.get();
            tradingState   = (char) buffer.get();
            reason         = readString(buffer, 4);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_STOCK_TRADING_ACTION);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) securityClass);
            buffer.put((byte) tradingState);
            buffer.put(reason.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * A Reg SHO Restriction (4.2.3) message.
     */
    public static class RegSHORestriction implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char regSHOAction;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            regSHOAction   = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_REG_SHO_RESTRICTION);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) regSHOAction);
        }
    }

    /**
     * An MWCB Decline Level (4.2.5.1) message.
     */
    public static class MWCBDeclineLevel implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public double level1;
        public double level2;
        public double level3;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            level1         = readPrice8(buffer);
            level2         = readPrice8(buffer);
            level3         = readPrice8(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MWCB_DECLINE_LEVEL);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            putPrice8(buffer, level1);
            putPrice8(buffer, level2);
            putPrice8(buffer, level3);
        }
    }

    /**
     * An MWCB Status (4.2.5.2) message.
     */
    public static class MWCBStatus implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public char breachedLevel;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            breachedLevel  = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MWCB_STATUS);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put((byte) breachedLevel);
        }
    }

    /**
     * An IPO Quoting Period Update (4.2.6) message.
     */
    public static class IPOQuotingPeriodUpdate implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public long ipoQuotationReleaseTime;
        public char ipoQuotationReleaseQualifier;
        public double ipoPrice;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber               = getUnsignedShort(buffer);
            timestampHigh                = getUnsignedShort(buffer);
            timestampLow                 = getUnsignedInt(buffer);
            stock                        = readString(buffer, 8);
            ipoQuotationReleaseTime      = getUnsignedInt(buffer);
            ipoQuotationReleaseQualifier = (char) buffer.get();
            ipoPrice                     = readDouble4(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            putUnsignedInt(buffer, ipoQuotationReleaseTime);
            buffer.put((byte) ipoQuotationReleaseQualifier);
            put(buffer, ipoPrice);
        }
    }

    /**
     * An Operational Halt (4.2.8) message.
     */
    public static class OperationalHalt implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char marketCode;
        public char operationalHaltAction;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber        = getUnsignedShort(buffer);
            timestampHigh         = getUnsignedShort(buffer);
            timestampLow          = getUnsignedInt(buffer);
            stock                 = readString(buffer, 8);
            marketCode            = (char) buffer.get();
            operationalHaltAction = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_OPERATIONAL_HALT);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) marketCode);
            buffer.put((byte) operationalHaltAction);
        }
    }

    /**
     * An NextShares Quotation (5.4) message.
     */
    public static class NextSharesQuotation implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char securityClass;
        public double bestBidProxyPrice;
        public long bestBidSize;
        public double bestBidAmount;
        public double bestOfferProxyPrice;
        public long bestOfferSize;
        public double bestOfferAmount;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            stock                = readString(buffer, 8);
            securityClass        = (char) buffer.get();
            bestBidProxyPrice    = readDouble4(buffer);
            bestBidSize          = getUnsignedInt(buffer);
            bestBidAmount        = readDouble4(buffer);
            bestOfferProxyPrice    = readDouble4(buffer);
            bestOfferSize          = getUnsignedInt(buffer);
            bestOfferAmount        = readDouble4(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ADD_ORDER);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) securityClass);
            put(buffer, bestBidProxyPrice);
            putUnsignedInt(buffer, bestBidSize);
            put(buffer, bestBidAmount);
            put(buffer, bestOfferProxyPrice);
            putUnsignedInt(buffer, bestOfferSize);
            put(buffer, bestOfferAmount);
        }
    }

    /**
     * A Cross Trade (5.3) message.
     */
    public static class Quotation implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char securityClass;
        public double bestBidPrice;
        public long bestBidSize;
        public double bestOfferPrice;
        public long bestOfferSize;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            securityClass  = (char) buffer.get();
            bestBidPrice   = readDouble4(buffer);
            bestBidSize    = getUnsignedInt(buffer);
            bestOfferPrice = readDouble4(buffer);
            bestOfferSize  = getUnsignedInt(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_CROSS_TRADE);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) securityClass);
            put(buffer, bestBidPrice);
            putUnsignedInt(buffer, bestBidSize);
            put(buffer, bestOfferPrice);
            putUnsignedInt(buffer, bestOfferSize);
        }
    }

    /**
     * An RPII (4.7) message.
     */
    public static class RPII implements Message {
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char interestFlag;

        @Override
        public void get(ByteBuffer buffer) {
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            interestFlag   = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_RPII);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) interestFlag);
        }
    }

}
