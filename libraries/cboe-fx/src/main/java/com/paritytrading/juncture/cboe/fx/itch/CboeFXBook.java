package com.paritytrading.juncture.cboe.fx.itch;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;

/**
 * Common definitions for Cboe FX Book Protocol.
 */
public class CboeFXBook {

    private CboeFXBook() {
    }

    public static final byte BUY  = 'B';
    public static final byte SELL = 'S';

    public static final byte MESSAGE_TYPE_NEW_ORDER       = 'N';
    public static final byte MESSAGE_TYPE_MODIFY_ORDER    = 'M';
    public static final byte MESSAGE_TYPE_CANCEL_ORDER    = 'X';
    public static final byte MESSAGE_TYPE_MARKET_SNAPSHOT = 'S';
    public static final byte MESSAGE_TYPE_TICKER          = 'T';

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
         * @throws BufferUnderflowException if there are fewer bytes remaining
         *   in the buffer than what this message consists of
         * @throws ReadOnlyBufferException if the buffer is read-only
         */
        void put(ByteBuffer buffer);

    }

    /**
     * A New Order message (2.2.1).
     */
    public static class NewOrder implements Message {
        public byte   buyOrSellIndicator;
        public byte[] currencyPair;
        public byte[] orderId;
        public byte[] price;
        public byte[] amount;
        public byte[] minqty;
        public byte[] lotsize;

        /**
         * Construct an instance.
         */
        public NewOrder() {
            currencyPair = new byte[7];
            orderId      = new byte[15];
            price        = new byte[10];
            amount       = new byte[16];
            minqty       = new byte[16];
            lotsize      = new byte[16];
        }

        @Override
        public void get(ByteBuffer buffer) {
            buyOrSellIndicator = buffer.get();
            buffer.get(currencyPair);
            buffer.get(orderId);
            buffer.get(price);
            buffer.get(amount);
            buffer.get(minqty);
            buffer.get(lotsize);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_NEW_ORDER);
            buffer.put(buyOrSellIndicator);
            buffer.put(currencyPair);
            buffer.put(orderId);
            buffer.put(price);
            buffer.put(amount);
            buffer.put(minqty);
            buffer.put(lotsize);
        }
    }

    /**
     * A Modify Order message (2.2.2).
     */
    public static class ModifyOrder implements Message {
        public byte[] currencyPair;
        public byte[] orderId;
        public byte[] amount;
        public byte[] minqty;
        public byte[] lotsize;

        /**
         * Construct an instance.
         */
        public ModifyOrder() {
            currencyPair = new byte[7];
            orderId      = new byte[15];
            amount       = new byte[16];
            minqty       = new byte[16];
            lotsize      = new byte[16];
        }

        @Override
        public void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
            buffer.get(orderId);
            buffer.get(amount);
            buffer.get(minqty);
            buffer.get(lotsize);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_MODIFY_ORDER);
            buffer.put(currencyPair);
            buffer.put(orderId);
            buffer.put(amount);
            buffer.put(minqty);
            buffer.put(lotsize);
        }
    }

    /**
     * A Cancel Order message (2.2.3).
     */
    public static class CancelOrder implements Message {
        public byte[] currencyPair;
        public byte[] orderId;

        /**
         * Construct an instance.
         */
        public CancelOrder() {
            currencyPair = new byte[7];
            orderId      = new byte[15];
        }

        @Override
        public void get(ByteBuffer buffer) {
            buffer.get(currencyPair);
            buffer.get(orderId);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_CANCEL_ORDER);
            buffer.put(currencyPair);
            buffer.put(orderId);
        }
    }

    /**
     * An entry in a Market Snapshot message (2.2.4).
     */
    public static class MarketSnapshotEntry {
        public byte[] currencyPair;
        public byte   buyOrSellIndicator;
        public byte[] price;
        public byte[] amount;
        public byte[] minqty;
        public byte[] lotsize;
        public byte[] orderId;

        /**
         * Construct an instance.
         */
        public MarketSnapshotEntry() {
            currencyPair = new byte[7];
            price        = new byte[10];
            amount       = new byte[16];
            minqty       = new byte[16];
            lotsize      = new byte[16];
            orderId      = new byte[15];
        }
    }

    /**
     * A Ticker message (2.2.5).
     */
    public static class Ticker implements Message {
        public byte   aggressorBuyOrSellIndicator;
        public byte[] currencyPair;
        public byte[] price;
        public byte[] transactionDate;
        public byte[] transactionTime;

        /**
         * Construct an instance.
         */
        public Ticker() {
            currencyPair    = new byte[7];
            price           = new byte[10];
            transactionDate = new byte[8];
            transactionTime = new byte[6];
        }

        @Override
        public void get(ByteBuffer buffer) {
            aggressorBuyOrSellIndicator = buffer.get();
            buffer.get(currencyPair);
            buffer.get(price);
            buffer.get(transactionDate);
            buffer.get(transactionTime);
        }

        @Override
        public void put(ByteBuffer buffer) {
            buffer.put(MESSAGE_TYPE_TICKER);
            buffer.put(aggressorBuyOrSellIndicator);
            buffer.put(currencyPair);
            buffer.put(price);
            buffer.put(transactionDate);
            buffer.put(transactionTime);
        }
    }

}
