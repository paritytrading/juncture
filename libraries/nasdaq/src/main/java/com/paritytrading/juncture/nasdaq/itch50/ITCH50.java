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
package com.paritytrading.juncture.nasdaq.itch50;

import static com.paritytrading.foundation.ByteBuffers.*;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.StandardCharsets;

/**
 * Common definitions.
 */
public class ITCH50 {

    private ITCH50() {
    }

    public static final byte BUY  = 'B';
    public static final byte SELL = 'S';

    public static final byte YES           = 'Y';
    public static final byte NO            = 'N';
    public static final byte NOT_AVAILABLE = ' ';

    public static final byte MESSAGE_TYPE_SYSTEM_EVENT                = 'S';
    public static final byte MESSAGE_TYPE_STOCK_DIRECTORY             = 'R';
    public static final byte MESSAGE_TYPE_STOCK_TRADING_ACTION        = 'H';
    public static final byte MESSAGE_TYPE_REG_SHO_RESTRICTION         = 'Y';
    public static final byte MESSAGE_TYPE_MARKET_PARTICIPANT_POSITION = 'L';
    public static final byte MESSAGE_TYPE_MWCB_DECLINE_LEVEL          = 'V';
    public static final byte MESSAGE_TYPE_MWCB_STATUS                 = 'W';
    public static final byte MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE   = 'K';
    public static final byte MESSAGE_TYPE_LULD_AUCTION_COLLAR         = 'J';
    public static final byte MESSAGE_TYPE_OPERATIONAL_HALT            = 'h';
    public static final byte MESSAGE_TYPE_ADD_ORDER                   = 'A';
    public static final byte MESSAGE_TYPE_ADD_ORDER_MPID              = 'F';
    public static final byte MESSAGE_TYPE_ORDER_EXECUTED              = 'E';
    public static final byte MESSAGE_TYPE_ORDER_EXECUTED_WITH_PRICE   = 'C';
    public static final byte MESSAGE_TYPE_ORDER_CANCEL                = 'X';
    public static final byte MESSAGE_TYPE_ORDER_DELETE                = 'D';
    public static final byte MESSAGE_TYPE_ORDER_REPLACE               = 'U';
    public static final byte MESSAGE_TYPE_TRADE                       = 'P';
    public static final byte MESSAGE_TYPE_CROSS_TRADE                 = 'Q';
    public static final byte MESSAGE_TYPE_BROKEN_TRADE                = 'B';
    public static final byte MESSAGE_TYPE_NOII                        = 'I';
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
     * Market Category (4.2.1) values.
     */
    public static final byte MARKET_CATEGORY_NASDAQ_GLOBAL_SELECT_MARKET = 'Q';
    public static final byte MARKET_CATEGORY_NASDAQ_GLOBAL_MARKET        = 'G';
    public static final byte MARKET_CATEGORY_NASDAQ_CAPITAL_MARKET       = 'S';
    public static final byte MARKET_CATEGORY_NYSE                        = 'N';
    public static final byte MARKET_CATEGORY_NYSE_MKT                    = 'A';
    public static final byte MARKET_CATEGORY_NYSE_ARCA                   = 'P';
    public static final byte MARKET_CATEGORY_BATS_Z_EXCHANGE             = 'Z';
    public static final byte MARKET_CATEGORY_NOT_AVAILABLE               = ' ';

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
     * Market Maker Mode (4.2.4) values.
     */
    public static final byte MARKET_MAKER_MODE_NORMAL        = 'N';
    public static final byte MARKET_MAKER_MODE_PASSIVE       = 'P';
    public static final byte MARKET_MAKER_MODE_SYNDICATE     = 'S';
    public static final byte MARKET_MAKER_MODE_PRE_SYNDICATE = 'R';
    public static final byte MARKET_MAKER_MODE_PENALTY       = 'L';

    /*
     * Market Participant State (4.2.4) values.
     */
    public static final byte MARKET_PARTICIPANT_STATE_ACTIVE            = 'A';
    public static final byte MARKET_PARTICIPANT_STATE_EXCUSED_WITHDRAWN = 'E';
    public static final byte MARKET_PARTICIPANT_STATE_WITHDRAWN         = 'W';
    public static final byte MARKET_PARTICIPANT_STATE_SUSPENDED         = 'S';
    public static final byte MARKET_PARTICIPANT_STATE_DELETED           = 'D';

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
     * Cross Type (4.5.2) values.
     */
    public static final byte CROSS_TYPE_OPENING_CROSS           = 'O';
    public static final byte CROSS_TYPE_CLOSING_CROSS           = 'C';
    public static final byte CROSS_TYPE_IPO_HALTED_PAUSED_CROSS = 'H';
    public static final byte CROSS_TYPE_INTRADAY_CROSS          = 'I';

    /*
     * Imbalance Direction (4.6) values.
     */
    public static final byte IMBALANCE_DIRECTION_BUY_IMBALANCE       = 'B';
    public static final byte IMBALANCE_DIRECTION_SELL_IMBALANCE      = 'S';
    public static final byte IMBALANCE_DIRECTION_NO_IMBALANCE        = 'N';
    public static final byte IMBALANCE_DIRECTION_INSUFFICIENT_ORDERS = 'O';

    /*
     * Price Variation Indicator (4.6) values.
     */
    public static final byte PRICE_VARIATION_INDICATOR_LESS_THAN_1_PCT   = 'L';
    public static final byte PRICE_VARIATION_INDICATOR_1_TO_1_99_PCT     = '1';
    public static final byte PRICE_VARIATION_INDICATOR_2_TO_2_99_PCT     = '2';
    public static final byte PRICE_VARIATION_INDICATOR_3_TO_3_99_PCT     = '3';
    public static final byte PRICE_VARIATION_INDICATOR_4_TO_4_99_PCT     = '4';
    public static final byte PRICE_VARIATION_INDICATOR_5_TO_5_99_PCT     = '5';
    public static final byte PRICE_VARIATION_INDICATOR_6_TO_6_99_PCT     = '6';
    public static final byte PRICE_VARIATION_INDICATOR_7_TO_7_99_PCT     = '7';
    public static final byte PRICE_VARIATION_INDICATOR_8_TO_8_99_PCT     = '8';
    public static final byte PRICE_VARIATION_INDICATOR_9_TO_9_99_PCT     = '9';
    public static final byte PRICE_VARIATION_INDICATOR_10_TO_19_99_PCT   = 'A';
    public static final byte PRICE_VARIATION_INDICATOR_20_TO_29_99_PCT   = 'B';
    public static final byte PRICE_VARIATION_INDICATOR_30_PCT_OR_GREATER = 'C';
    public static final byte PRICE_VARIATION_INDICATOR_NOT_AVAILABLE     = ' ';

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
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public char eventCode;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            eventCode      = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_SYSTEM_EVENT);
            putUnsignedShort(buffer, stockLocate);
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
        public int   stockLocate;
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
            stockLocate                 = getUnsignedShort(buffer);
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
            putUnsignedShort(buffer, stockLocate);
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
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char tradingState;
        public byte reserved;
        public String  reason;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            tradingState   = (char) buffer.get();
            reserved       = buffer.get();
            reason         = readString(buffer, 4);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_STOCK_TRADING_ACTION);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) tradingState);
            buffer.put(reserved);
            buffer.put(reason.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * A Reg SHO Restriction (4.2.3) message.
     */
    public static class RegSHORestriction implements Message {
        public int  locateCode;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char regSHOAction;

        @Override
        public void get(ByteBuffer buffer) {
            locateCode     = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            regSHOAction   = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_REG_SHO_RESTRICTION);
            putUnsignedShort(buffer, locateCode);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) regSHOAction);
        }
    }

    /**
     * A Market Participant Position (4.2.4) message.
     */
    public static class MarketParticipantPosition implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String  mpid;
        public String stock;
        public char primaryMarketMaker;
        public char marketMakerMode;
        public char marketParticipantState;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate            = getUnsignedShort(buffer);
            trackingNumber         = getUnsignedShort(buffer);
            timestampHigh          = getUnsignedShort(buffer);
            timestampLow           = getUnsignedInt(buffer);
            mpid                   = readString(buffer, 4);
            stock                  = readString(buffer, 8);
            primaryMarketMaker     = (char) buffer.get();
            marketMakerMode        = (char) buffer.get();
            marketParticipantState = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MARKET_PARTICIPANT_POSITION);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(mpid.getBytes(StandardCharsets.UTF_8));
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) primaryMarketMaker);
            buffer.put((byte) marketMakerMode);
            buffer.put((byte) marketParticipantState);
        }
    }

    /**
     * An MWCB Decline Level (4.2.5.1) message.
     */
    public static class MWCBDeclineLevel implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public double level1;
        public double level2;
        public double level3;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
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
            putUnsignedShort(buffer, stockLocate);
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
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public char breachedLevel;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            breachedLevel  = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MWCB_STATUS);
            putUnsignedShort(buffer, stockLocate);
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
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public long ipoQuotationReleaseTime;
        public char ipoQuotationReleaseQualifier;
        public double ipoPrice;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate                  = getUnsignedShort(buffer);
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
            putUnsignedShort(buffer, stockLocate);
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
     * A LULD Auction Collar (4.2.7) message.
     */
    public static class LULDAuctionCollar implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public double auctionCollarReferencePrice;
        public double upperAuctionCollarPrice;
        public double lowerAuctionCollarPrice;
        public long auctionCollarExtension;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate                 = getUnsignedShort(buffer);
            trackingNumber              = getUnsignedShort(buffer);
            timestampHigh               = getUnsignedShort(buffer);
            timestampLow                = getUnsignedInt(buffer);
            stock                       = readString(buffer, 8);
            auctionCollarReferencePrice = readDouble4(buffer);
            upperAuctionCollarPrice     = readDouble4(buffer);
            lowerAuctionCollarPrice     = readDouble4(buffer);
            auctionCollarExtension      = getUnsignedInt(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_LULD_AUCTION_COLLAR);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            put(buffer, auctionCollarReferencePrice);
            put(buffer, upperAuctionCollarPrice);
            put(buffer, lowerAuctionCollarPrice);
            putUnsignedInt(buffer, auctionCollarExtension);
        }
    }

    /**
     * An Operational Halt (4.2.8) message.
     */
    public static class OperationalHalt implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char marketCode;
        public char operationalHaltAction;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate           = getUnsignedShort(buffer);
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
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) marketCode);
            buffer.put((byte) operationalHaltAction);
        }
    }

    /**
     * An Add Order (4.3.1) message.
     */
    public static class AddOrder implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;
        public char buySellIndicator;
        public long shares;
        public String stock;
        public double price;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            buySellIndicator     = (char) buffer.get();
            shares               = getUnsignedInt(buffer);
            stock                = readString(buffer, 8);
            price                = readDouble4(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ADD_ORDER);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            buffer.put((byte) buySellIndicator);
            putUnsignedInt(buffer, shares);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            put(buffer, price);
        }
    }

    /**
     * An Add Order with MPID (4.3.2) message.
     */
    public static class AddOrderMPID implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;
        public char buySellIndicator;
        public long shares;
        public String stock;
        public double price;
        public String attribution;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            buySellIndicator     = (char) buffer.get();
            shares               = getUnsignedInt(buffer);
            stock                = readString(buffer, 8);
            price                = readDouble4(buffer);
            attribution          = readString(buffer, 4);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ADD_ORDER_MPID);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            buffer.put((byte) buySellIndicator);
            putUnsignedInt(buffer, shares);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            put(buffer, price);
            buffer.put(attribution.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * An Order Executed (4.4.1) message.
     */
    public static class OrderExecuted implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;
        public long executedShares;
        public long matchNumber;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            executedShares       = getUnsignedInt(buffer);
            matchNumber          = buffer.getLong();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ORDER_EXECUTED);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            putUnsignedInt(buffer, executedShares);
            buffer.putLong(matchNumber);
        }
    }

    /**
     * An Order Executed With Price (4.4.2) message.
     */
    public static class OrderExecutedWithPrice implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;
        public long executedShares;
        public long matchNumber;
        public char printable;
        public double executionPrice;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            executedShares       = getUnsignedInt(buffer);
            matchNumber          = buffer.getLong();
            printable            = (char) buffer.get();
            executionPrice       = readDouble4(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ORDER_EXECUTED_WITH_PRICE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            putUnsignedInt(buffer, executedShares);
            buffer.putLong(matchNumber);
            buffer.put((byte) printable);
            put(buffer, executionPrice);
        }
    }

    /**
     * An Order Cancel (4.4.3) message.
     */
    public static class OrderCancel implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;
        public long canceledShares;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            canceledShares       = getUnsignedInt(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ORDER_CANCEL);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            putUnsignedInt(buffer, canceledShares);
        }
    }

    /**
     * An Order Delete (4.4.4) message.
     */
    public static class OrderDelete implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ORDER_DELETE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
        }
    }

    /**
     * An Order Replace (4.4.5) message.
     */
    public static class OrderReplace implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long originalOrderReferenceNumber;
        public long newOrderReferenceNumber;
        public long shares;
        public double price;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate                  = getUnsignedShort(buffer);
            trackingNumber               = getUnsignedShort(buffer);
            timestampHigh                = getUnsignedShort(buffer);
            timestampLow                 = getUnsignedInt(buffer);
            originalOrderReferenceNumber = buffer.getLong();
            newOrderReferenceNumber      = buffer.getLong();
            shares                       = getUnsignedInt(buffer);
            price                        = readDouble4(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ORDER_REPLACE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(originalOrderReferenceNumber);
            buffer.putLong(newOrderReferenceNumber);
            putUnsignedInt(buffer, shares);
            put(buffer, price);
        }
    }

    /**
     * A Trade (4.5.1) message.
     */
    public static class Trade implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long orderReferenceNumber;
        public char buySellIndicator;
        public long shares;
        public String stock;
        public double price;
        public long matchNumber;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            buySellIndicator     = (char) buffer.get();
            shares               = getUnsignedInt(buffer);
            stock                = readString(buffer, 8);
            price                = readDouble4(buffer);
            matchNumber          = buffer.getLong();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_TRADE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            buffer.put((byte) buySellIndicator);
            putUnsignedInt(buffer, shares);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            put(buffer, price);
            buffer.putLong(matchNumber);
        }
    }

    /**
     * A Cross Trade (4.5.2) message.
     */
    public static class CrossTrade implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long shares;
        public String stock;
        public double crossPrice;
        public long matchNumber;
        public char crossType;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            shares         = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            crossPrice     = readDouble4(buffer);
            matchNumber    = buffer.getLong();
            crossType      = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_CROSS_TRADE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            putUnsignedInt(buffer, shares);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            put(buffer, crossPrice);
            buffer.putLong(matchNumber);
            buffer.put((byte) crossType);
        }
    }

    /**
     * A Broken Trade (4.5.3) message.
     */
    public static class BrokenTrade implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long matchNumber;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            matchNumber    = buffer.getLong();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_BROKEN_TRADE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(matchNumber);
        }
    }

    /**
     * A NOII (4.6) message.
     */
    public static class NOII implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public long pairedShares;
        public long imbalanceShares;
        public char imbalanceDirection;
        public String stock;
        public double farPrice;
        public double nearPrice;
        public double currentReferencePrice;
        public char crossType;
        public char priceVariationIndicator;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate             = getUnsignedShort(buffer);
            trackingNumber          = getUnsignedShort(buffer);
            timestampHigh           = getUnsignedShort(buffer);
            timestampLow            = getUnsignedInt(buffer);
            pairedShares            = buffer.getLong();
            imbalanceShares         = buffer.getLong();
            imbalanceDirection      = (char) buffer.get();
            stock                   = readString(buffer, 8);
            farPrice                = readDouble4(buffer);
            nearPrice               = readDouble4(buffer);
            currentReferencePrice   = readDouble4(buffer);
            crossType               = (char) buffer.get();
            priceVariationIndicator = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_NOII);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(pairedShares);
            buffer.putLong(imbalanceShares);
            buffer.put((byte) imbalanceDirection);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            put(buffer, farPrice);
            put(buffer, nearPrice);
            put(buffer, currentReferencePrice);
            buffer.put((byte) crossType);
            buffer.put((byte) priceVariationIndicator);
        }
    }

    /**
     * An RPII (4.7) message.
     */
    public static class RPII implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public String stock;
        public char interestFlag;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = readString(buffer, 8);
            interestFlag   = (char) buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_RPII);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(stock.getBytes(StandardCharsets.UTF_8));
            buffer.put((byte) interestFlag);
        }
    }

}
