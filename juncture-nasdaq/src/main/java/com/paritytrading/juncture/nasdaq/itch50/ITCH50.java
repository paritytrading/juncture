package com.paritytrading.juncture.nasdaq.itch50;

import static org.jvirtanen.nio.ByteBuffers.*;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;

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

    }

    /**
     * A System Event message.
     */
    public static class SystemEvent implements Message {
        public int  stockLocate;
        public int  trackingNumber;
        public int  timestampHigh;
        public long timestampLow;
        public byte eventCode;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            eventCode      = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_SYSTEM_EVENT);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(eventCode);
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
        public long  stock;
        public byte  marketCategory;
        public byte  financialStatusIndicator;
        public long  roundLotSize;
        public byte  roundLotsOnly;
        public byte  issueClassification;
        public short issueSubType;
        public byte  authenticity;
        public byte  shortSaleThresholdIndicator;
        public byte  ipoFlag;
        public byte  luldReferencePriceTier;
        public byte  etpFlag;
        public long  etpLeverageFactor;
        public byte  inverseIndicator;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate                 = getUnsignedShort(buffer);
            trackingNumber              = getUnsignedShort(buffer);
            timestampHigh               = getUnsignedShort(buffer);
            timestampLow                = getUnsignedInt(buffer);
            stock                       = buffer.getLong();
            marketCategory              = buffer.get();
            financialStatusIndicator    = buffer.get();
            roundLotSize                = getUnsignedInt(buffer);
            roundLotsOnly               = buffer.get();
            issueClassification         = buffer.get();
            issueSubType                = buffer.getShort();
            authenticity                = buffer.get();
            shortSaleThresholdIndicator = buffer.get();
            ipoFlag                     = buffer.get();
            luldReferencePriceTier      = buffer.get();
            etpFlag                     = buffer.get();
            etpLeverageFactor           = getUnsignedInt(buffer);
            inverseIndicator            = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_STOCK_DIRECTORY);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(stock);
            buffer.put(marketCategory);
            buffer.put(financialStatusIndicator);
            putUnsignedInt(buffer, roundLotSize);
            buffer.put(roundLotsOnly);
            buffer.put(issueClassification);
            buffer.putShort(issueSubType);
            buffer.put(authenticity);
            buffer.put(shortSaleThresholdIndicator);
            buffer.put(ipoFlag);
            buffer.put(luldReferencePriceTier);
            buffer.put(etpFlag);
            putUnsignedInt(buffer, etpLeverageFactor);
            buffer.put(inverseIndicator);
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
        public long stock;
        public byte tradingState;
        public byte reserved;
        public int  reason;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = buffer.getLong();
            tradingState   = buffer.get();
            reserved       = buffer.get();
            reason         = buffer.getInt();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_STOCK_TRADING_ACTION);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(stock);
            buffer.put(tradingState);
            buffer.put(reserved);
            buffer.putInt(reason);
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
        public long stock;
        public byte regSHOAction;

        @Override
        public void get(ByteBuffer buffer) {
            locateCode     = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = buffer.getLong();
            regSHOAction   = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_REG_SHO_RESTRICTION);
            putUnsignedShort(buffer, locateCode);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(stock);
            buffer.put(regSHOAction);
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
        public int  mpid;
        public long stock;
        public byte primaryMarketMaker;
        public byte marketMakerMode;
        public byte marketParticipantState;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate            = getUnsignedShort(buffer);
            trackingNumber         = getUnsignedShort(buffer);
            timestampHigh          = getUnsignedShort(buffer);
            timestampLow           = getUnsignedInt(buffer);
            mpid                   = buffer.getInt();
            stock                  = buffer.getLong();
            primaryMarketMaker     = buffer.get();
            marketMakerMode        = buffer.get();
            marketParticipantState = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MARKET_PARTICIPANT_POSITION);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putInt(mpid);
            buffer.putLong(stock);
            buffer.put(primaryMarketMaker);
            buffer.put(marketMakerMode);
            buffer.put(marketParticipantState);
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
        public long level1;
        public long level2;
        public long level3;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            level1         = buffer.getLong();
            level2         = buffer.getLong();
            level3         = buffer.getLong();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MWCB_DECLINE_LEVEL);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(level1);
            buffer.putLong(level2);
            buffer.putLong(level3);
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
        public byte breachedLevel;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            breachedLevel  = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MWCB_STATUS);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.put(breachedLevel);
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
        public long stock;
        public long ipoQuotationReleaseTime;
        public byte ipoQuotationReleaseQualifier;
        public long ipoPrice;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate                  = getUnsignedShort(buffer);
            trackingNumber               = getUnsignedShort(buffer);
            timestampHigh                = getUnsignedShort(buffer);
            timestampLow                 = getUnsignedInt(buffer);
            stock                        = buffer.getLong();
            ipoQuotationReleaseTime      = getUnsignedInt(buffer);
            ipoQuotationReleaseQualifier = buffer.get();
            ipoPrice                     = getUnsignedInt(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(stock);
            putUnsignedInt(buffer, ipoQuotationReleaseTime);
            buffer.put(ipoQuotationReleaseQualifier);
            putUnsignedInt(buffer, ipoPrice);
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
        public byte buySellIndicator;
        public long shares;
        public long stock;
        public long price;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            buySellIndicator     = buffer.get();
            shares               = getUnsignedInt(buffer);
            stock                = buffer.getLong();
            price                = getUnsignedInt(buffer);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ADD_ORDER);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            buffer.put(buySellIndicator);
            putUnsignedInt(buffer, shares);
            buffer.putLong(stock);
            putUnsignedInt(buffer, price);
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
        public byte buySellIndicator;
        public long shares;
        public long stock;
        public long price;
        public int  attribution;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            buySellIndicator     = buffer.get();
            shares               = getUnsignedInt(buffer);
            stock                = buffer.getLong();
            price                = getUnsignedInt(buffer);
            attribution          = buffer.getInt();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_ADD_ORDER_MPID);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(orderReferenceNumber);
            buffer.put(buySellIndicator);
            putUnsignedInt(buffer, shares);
            buffer.putLong(stock);
            putUnsignedInt(buffer, price);
            buffer.putInt(attribution);
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
        public byte printable;
        public long executionPrice;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            executedShares       = getUnsignedInt(buffer);
            matchNumber          = buffer.getLong();
            printable            = buffer.get();
            executionPrice       = getUnsignedInt(buffer);
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
            buffer.put(printable);
            putUnsignedInt(buffer, executionPrice);
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
        public long price;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate                  = getUnsignedShort(buffer);
            trackingNumber               = getUnsignedShort(buffer);
            timestampHigh                = getUnsignedShort(buffer);
            timestampLow                 = getUnsignedInt(buffer);
            originalOrderReferenceNumber = buffer.getLong();
            newOrderReferenceNumber      = buffer.getLong();
            shares                       = getUnsignedInt(buffer);
            price                        = getUnsignedInt(buffer);
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
            putUnsignedInt(buffer, price);
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
        public byte buySellIndicator;
        public long shares;
        public long stock;
        public long price;
        public long matchNumber;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate          = getUnsignedShort(buffer);
            trackingNumber       = getUnsignedShort(buffer);
            timestampHigh        = getUnsignedShort(buffer);
            timestampLow         = getUnsignedInt(buffer);
            orderReferenceNumber = buffer.getLong();
            buySellIndicator     = buffer.get();
            shares               = getUnsignedInt(buffer);
            stock                = buffer.getLong();
            price                = getUnsignedInt(buffer);
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
            buffer.put(buySellIndicator);
            putUnsignedInt(buffer, shares);
            buffer.putLong(stock);
            putUnsignedInt(buffer, price);
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
        public long stock;
        public long crossPrice;
        public long matchNumber;
        public byte crossType;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            shares         = getUnsignedInt(buffer);
            stock          = buffer.getLong();
            crossPrice     = getUnsignedInt(buffer);
            matchNumber    = buffer.getLong();
            crossType      = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_CROSS_TRADE);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            putUnsignedInt(buffer, shares);
            buffer.putLong(stock);
            putUnsignedInt(buffer, crossPrice);
            buffer.putLong(matchNumber);
            buffer.put(crossType);
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
        public byte imbalanceDirection;
        public long stock;
        public long farPrice;
        public long nearPrice;
        public long currentReferencePrice;
        public byte crossType;
        public byte priceVariationIndicator;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate             = getUnsignedShort(buffer);
            trackingNumber          = getUnsignedShort(buffer);
            timestampHigh           = getUnsignedShort(buffer);
            timestampLow            = getUnsignedInt(buffer);
            pairedShares            = buffer.getLong();
            imbalanceShares         = buffer.getLong();
            imbalanceDirection      = buffer.get();
            stock                   = buffer.getLong();
            farPrice                = getUnsignedInt(buffer);
            nearPrice               = getUnsignedInt(buffer);
            currentReferencePrice   = getUnsignedInt(buffer);
            crossType               = buffer.get();
            priceVariationIndicator = buffer.get();
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
            buffer.put(imbalanceDirection);
            buffer.putLong(stock);
            putUnsignedInt(buffer, farPrice);
            putUnsignedInt(buffer, nearPrice);
            putUnsignedInt(buffer, currentReferencePrice);
            buffer.put(crossType);
            buffer.put(priceVariationIndicator);
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
        public long stock;
        public byte interestFlag;

        @Override
        public void get(ByteBuffer buffer) {
            stockLocate    = getUnsignedShort(buffer);
            trackingNumber = getUnsignedShort(buffer);
            timestampHigh  = getUnsignedShort(buffer);
            timestampLow   = getUnsignedInt(buffer);
            stock          = buffer.getLong();
            interestFlag   = buffer.get();
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_RPII);
            putUnsignedShort(buffer, stockLocate);
            putUnsignedShort(buffer, trackingNumber);
            putUnsignedShort(buffer, timestampHigh);
            putUnsignedInt(buffer, timestampLow);
            buffer.putLong(stock);
            buffer.put(interestFlag);
        }
    }

}
