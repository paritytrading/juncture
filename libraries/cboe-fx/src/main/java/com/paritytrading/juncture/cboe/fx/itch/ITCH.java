package com.paritytrading.juncture.cboe.fx.itch;

import com.paritytrading.foundation.ASCII;
import java.nio.ByteBuffer;

/**
 * Common definitions for ITCH Session Management Protocol.
 */
public class ITCH {

    public static final int MAX_NUMBER_OF_CURRENCY_PAIRS = 256;

    /*
     * These messages are sent by the server to the client.
     */
    static final byte MESSAGE_TYPE_LOGIN_ACCEPTED       = 'A';
    static final byte MESSAGE_TYPE_LOGIN_REJECTED       = 'J';
    static final byte MESSAGE_TYPE_SEQUENCED_DATA       = 'S';
    static final byte MESSAGE_TYPE_SERVER_HEARTBEAT     = 'H';
    static final byte MESSAGE_TYPE_ERROR_NOTIFICATION   = 'E';
    static final byte MESSAGE_TYPE_INSTRUMENT_DIRECTORY = 'R';

    /*
     * These messages are sent by the client to the server.
     */
    static final byte MESSAGE_TYPE_LOGIN_REQUEST                   = 'L';
    static final byte MESSAGE_TYPE_LOGOUT_REQUEST                  = 'O';
    static final byte MESSAGE_TYPE_CLIENT_HEARTBEAT                = 'R';
    static final byte MESSAGE_TYPE_MARKET_SNAPSHOT_REQUEST         = 'M';
    static final byte MESSAGE_TYPE_TICKER_SUBSCRIBE_REQUEST        = 'T';
    static final byte MESSAGE_TYPE_TICKER_UNSUBSCRIBE_REQUEST      = 'U';
    static final byte MESSAGE_TYPE_MARKET_DATA_SUBSCRIBE_REQUEST   = 'A';
    static final byte MESSAGE_TYPE_MARKET_DATA_UNSUBSCRIBE_REQUEST = 'B';
    static final byte MESSAGE_TYPE_INSTRUMENT_DIRECTORY_REQUEST    = 'I';

    public static final byte TRUE  = 'T';
    public static final byte FALSE = 'F';

    /**
     * A Login Accepted packet (1.2.1).
     */
    public static class LoginAccepted {
        public byte[] sequenceNumber;

        /**
         * Create a new instance.
         */
        public LoginAccepted() {
            sequenceNumber = new byte[10];
        }

        void get(ByteBuffer buffer) {
            buffer.get(sequenceNumber);
        }

        void put(ByteBuffer buffer) {
            buffer.put(sequenceNumber);
        }
    }

    /**
     * A Login Rejected packet (1.2.2).
     */
    public static class LoginRejected {
        public byte[] reason;

        /**
         * Create a new instance.
         */
        public LoginRejected() {
            reason = new byte[20];
        }

        void get(ByteBuffer buffer) {
            buffer.get(reason);
        }

        void put(ByteBuffer buffer) {
            buffer.put(reason);
        }
    }

    /**
     * A Sequenced Data packet (1.2.3).
     */
    public static class SequencedData {
        public byte[] time;

        /**
         * Create a new instance.
         */
        public SequencedData() {
            time = new byte[9];
        }

        void get(ByteBuffer buffer) {
            buffer.get(time);
        }

        void put(ByteBuffer buffer) {
            buffer.put(time);
        }
    }

    /**
     * An Error Notification packet (1.2.6).
     */
    public static class ErrorNotification {
        public byte[] errorExplanation;

        /**
         * Create a new instance.
         */
        public ErrorNotification() {
            errorExplanation = new byte[100];
        }

        void get(ByteBuffer buffer) {
            buffer.get(errorExplanation);
        }

        void put(ByteBuffer buffer) {
            buffer.put(errorExplanation);
        }
    }

    /**
     * An Instrument Directory packet (1.2.7).
     */
    public static class InstrumentDirectory {
        public byte[]   numberOfCurrencyPairs;
        public byte[][] currencyPair;

        public InstrumentDirectory() {
            numberOfCurrencyPairs = new byte[4];
            currencyPair          = new byte[MAX_NUMBER_OF_CURRENCY_PAIRS][7];
        }

        void get(ByteBuffer buffer) {
            buffer.get(numberOfCurrencyPairs);

            for (int i = 0; i < ASCII.getLong(numberOfCurrencyPairs); i++)
                buffer.get(currencyPair[i]);
        }

        void put(ByteBuffer buffer) {
            buffer.put(numberOfCurrencyPairs);

            for (int i = 0; i < ASCII.getLong(numberOfCurrencyPairs); i++)
                buffer.put(currencyPair[i]);
        }
    }

    /**
     * A Login Request packet (1.3.1).
     */
    public static class LoginRequest {
        public byte[] loginName;
        public byte[] password;
        public byte   marketDataUnsubscribe;
        public byte[] reserved;

        /**
         * Create an instance.
         */
        public LoginRequest() {
            loginName = new byte[40];
            password  = new byte[40];
            reserved  = new byte[9];
        }

        void get(ByteBuffer buffer) {
            buffer.get(loginName);
            buffer.get(password);
            marketDataUnsubscribe = buffer.get();
            buffer.get(reserved);
        }

        void put(ByteBuffer buffer) {
            buffer.put(loginName);
            buffer.put(password);
            buffer.put(marketDataUnsubscribe);
            buffer.put(reserved);
        }
    }

    /**
     * A Market Snapshot Request packet (1.3.4).
     */
    public static class MarketSnapshotRequest {
        public byte[] currencyPair;

        /**
         * Construct an instance.
         */
        public MarketSnapshotRequest() {
            currencyPair = new byte[7];
        }

        void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
        }

        void put(ByteBuffer buffer) {
            buffer.put(currencyPair);
        }
    }

    /**
     * A Ticker Subscribe Request packet (1.3.5).
     */
    public static class TickerSubscribeRequest {
        public byte[] currencyPair;

        /**
         * Construct an instance.
         */
        public TickerSubscribeRequest() {
            currencyPair = new byte[7];
        }

        void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
        }

        void put(ByteBuffer buffer) {
            buffer.put(currencyPair);
        }
    }

    /**
     * A Ticker Unsubscribe Request packet (1.3.6).
     */
    public static class TickerUnsubscribeRequest {
        public byte[] currencyPair;

        /**
         * Construct an instance.
         */
        public TickerUnsubscribeRequest() {
            currencyPair = new byte[7];
        }

        void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
        }

        void put(ByteBuffer buffer) {
            buffer.put(currencyPair);
        }
    }

    /**
     * A Market Data Subscribe Request packet (1.3.7).
     */
    public static class MarketDataSubscribeRequest {
        public byte[] currencyPair;

        /**
         * Construct an instance.
         */
        public MarketDataSubscribeRequest() {
            currencyPair = new byte[7];
        }

        void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
        }

        void put(ByteBuffer buffer) {
            buffer.put(currencyPair);
        }
    }

    /**
     * A Market Data Unsubscribe Request packet (1.3.8).
     */
    public static class MarketDataUnsubscribeRequest {
        public byte[] currencyPair;

        /**
         * Construct an instance.
         */
        public MarketDataUnsubscribeRequest() {
            currencyPair = new byte[7];
        }

        void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
        }

        void put(ByteBuffer buffer) {
            buffer.put(currencyPair);
        }
    }

    private ITCH() {
    }

}
