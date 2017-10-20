package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.foundation.ByteBuffers.*;
import static com.paritytrading.juncture.cboe.fx.itch.CboeFXBook.*;

import com.paritytrading.foundation.ASCII;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * A formatter for outbound Cboe FX Book Protocol messages.
 */
public class CboeFXBookFormatter {

    private State state;

    /**
     * Create a formatter for outbound Cboe FX Book Protocol messages.
     */
    public CboeFXBookFormatter() {
        state = new State();
    }

    /**
     * Start a Market Snapshot message.
     *
     * @param buffer a buffer
     */
    public void marketSnapshotStart(ByteBuffer buffer) {
        state.reset();

        // Message Type
        buffer.put(MESSAGE_TYPE_MARKET_SNAPSHOT);

        state.lengthOfMessagePosition = buffer.position();

        // Length of Message
        ASCII.putLongRight(state.lengthOfMessage, 0);
        buffer.put(state.lengthOfMessage);

        state.numberOfCurrencyPairsPosition = buffer.position();
    }

    /**
     * <p>Add an entry to a Market Snapshot message. Subsequent entries must be
     * grouped by the following fields:</p>
     *
     * <ol>
     *   <li>currency pair</li>
     *   <li>buy or sell indicator</li>
     *   <li>price</li>
     * </ol>
     *
     * @param buffer a buffer
     * @param entry an entry
     */
    public void marketSnapshotEntry(ByteBuffer buffer, MarketSnapshotEntry entry) {
        if (!Arrays.equals(entry.currencyPair, state.currencyPair)) {
            if (state.currencyPairs == 0) {

                // Number of Currency Pairs
                ASCII.putLongRight(state.numberOfItems, 0);
                buffer.put(state.numberOfItems);
            }

            state.currencyPairs++;

            System.arraycopy(entry.currencyPair, 0, state.currencyPair, 0, state.currencyPair.length);

            // Currency Pair
            buffer.put(entry.currencyPair);

            state.buyOrSellIndicator = entry.buyOrSellIndicator;

            state.numberOfPricesPosition = buffer.position();

            // Number of Bid Prices
            ASCII.putLongRight(state.numberOfItems, 0);
            buffer.put(state.numberOfItems);

            if (entry.buyOrSellIndicator == SELL) {
                state.numberOfPricesPosition = buffer.position();

                // Number of Offer Prices
                ASCII.putLongRight(state.numberOfItems, 0);
                buffer.put(state.numberOfItems);
            }

            System.arraycopy(entry.price, 0, state.price, 0, state.price.length);

            // Bid/Offer Price
            buffer.put(entry.price);

            state.numberOfOrdersPosition = buffer.position();

            // Number of Bid/Offer Orders
            ASCII.putLongRight(state.numberOfItems, 0);
            buffer.put(state.numberOfItems);

            state.prices = 1;
            state.orders = 0;
        }
        else if (entry.buyOrSellIndicator != state.buyOrSellIndicator) {

            // Number of Bid Prices
            ASCII.putLongRight(state.numberOfItems, state.prices);
            put(buffer, state.numberOfItems, state.numberOfPricesPosition);

            // Number of Bid Orders
            ASCII.putLongRight(state.numberOfItems, state.orders);
            put(buffer, state.numberOfItems, state.numberOfOrdersPosition);

            state.numberOfPricesPosition = buffer.position();

            state.buyOrSellIndicator = entry.buyOrSellIndicator;

            // Number of Offer Prices
            ASCII.putLongRight(state.numberOfItems, 0);
            buffer.put(state.numberOfItems);

            System.arraycopy(entry.price, 0, state.price, 0, state.price.length);

            // Offer Price
            buffer.put(entry.price);

            state.numberOfOrdersPosition = buffer.position();

            // Number of Offer Orders
            ASCII.putLongRight(state.numberOfItems, 0);
            buffer.put(state.numberOfItems);

            state.prices = 1;
            state.orders = 0;
        }
        else if (!Arrays.equals(entry.price, state.price)) {

            // Number of Bid/Offer Orders
            ASCII.putLongRight(state.numberOfItems, state.orders);
            put(buffer, state.numberOfItems, state.numberOfOrdersPosition);

            // Bid/Offer Price
            buffer.put(entry.price);

            state.numberOfOrdersPosition = buffer.position();

            // Number of Bid/Offer Orders
            ASCII.putLongRight(state.numberOfItems, 0);
            buffer.put(state.numberOfItems);

            state.orders = 0;

            state.prices++;
        }

        buffer.put(entry.amount);
        buffer.put(entry.minqty);
        buffer.put(entry.lotsize);
        buffer.put(entry.orderId);

        state.orders++;
    }

    /**
     * End a Market Snapshot message.
     *
     * @param buffer a buffer
     */
    public void marketSnapshotEnd(ByteBuffer buffer) {
        if (state.currencyPairs > 0) {

            // Number of Currency Pairs
            ASCII.putLongRight(state.numberOfItems, state.currencyPairs);
            put(buffer, state.numberOfItems, state.numberOfCurrencyPairsPosition);
        }

        if (state.buyOrSellIndicator == BUY) {

            // Number Of Offer Prices
            ASCII.putLongRight(state.numberOfItems, 0);
            buffer.put(state.numberOfItems);
        }

        if (state.prices > 0) {

            // Number of Bid/Offer Prices
            ASCII.putLongRight(state.numberOfItems, state.prices);
            put(buffer, state.numberOfItems, state.numberOfPricesPosition);
        }

        if (state.orders > 0) {

            // Number of Bid/Offer Orders
            ASCII.putLongRight(state.numberOfItems, state.orders);
            put(buffer, state.numberOfItems, state.numberOfOrdersPosition);
        }

        int endPosition = buffer.position();

        int lengthOfMessage = endPosition - state.numberOfCurrencyPairsPosition;

        // Length of Message
        ASCII.putLongRight(state.lengthOfMessage, lengthOfMessage);
        put(buffer, state.lengthOfMessage, state.lengthOfMessagePosition);
    }

    private static class State {
        public byte[] lengthOfMessage;
        public byte[] numberOfItems;

        public int lengthOfMessagePosition;
        public int numberOfCurrencyPairsPosition;
        public int numberOfPricesPosition;
        public int numberOfOrdersPosition;

        public int currencyPairs;
        public int prices;
        public int orders;

        public byte[] currencyPair;
        public byte   buyOrSellIndicator;
        public byte[] price;

        public State() {
            lengthOfMessage = new byte[6];
            numberOfItems   = new byte[4];

            currencyPair = new byte[7];
            price        = new byte[10];

            reset();
        }

        public void reset() {
            ASCII.putLongRight(lengthOfMessage, 0);
            ASCII.putLongRight(numberOfItems, 0);

            lengthOfMessagePosition = -1;
            numberOfCurrencyPairsPosition = -1;
            numberOfPricesPosition = -1;
            numberOfOrdersPosition = -1;

            currencyPairs = 0;
            prices        = 0;
            orders        = 0;

            ASCII.putLeft(currencyPair, " ");
            buyOrSellIndicator = ' ';
            ASCII.putLeft(price, " ");
        }
    }

}
