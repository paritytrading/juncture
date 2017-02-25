package com.paritytrading.juncture.hotspot.itch;

import static com.paritytrading.juncture.hotspot.itch.HotspotBook.*;

import com.paritytrading.foundation.ASCII;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A parser for inbound Hotspot Book Protocol messages.
 */
public class HotspotBookParser {

    private NewOrder            newOrder;
    private ModifyOrder         modifyOrder;
    private CancelOrder         cancelOrder;
    private MarketSnapshotEntry marketSnapshotEntry;
    private Ticker              ticker;

    private State state;

    private HotspotBookListener listener;

    /**
     * Create a parser for inbound Hotspot Book Protocol messages.
     *
     * @param listener the listener
     */
    public HotspotBookParser(HotspotBookListener listener) {
        this.newOrder            = new NewOrder();
        this.modifyOrder         = new ModifyOrder();
        this.cancelOrder         = new CancelOrder();
        this.marketSnapshotEntry = new MarketSnapshotEntry();
        this.ticker              = new Ticker();

        this.state = new State();

        this.listener = listener;
    }

    /**
     * Parse a Hotspot Book Protocol message.
     *
     * @param buffer a buffer containing a Hotspot Book Protocol message
     * @throws IOException if an I/O error occurs
     */
    public void parse(ByteBuffer buffer) throws IOException {
        byte messageType = buffer.get();

        switch (messageType) {
        case MESSAGE_TYPE_NEW_ORDER:
            newOrder.get(buffer);
            listener.newOrder(newOrder);
            break;
        case MESSAGE_TYPE_MODIFY_ORDER:
            modifyOrder.get(buffer);
            listener.modifyOrder(modifyOrder);
            break;
        case MESSAGE_TYPE_CANCEL_ORDER:
            cancelOrder.get(buffer);
            listener.cancelOrder(cancelOrder);
            break;
        case MESSAGE_TYPE_MARKET_SNAPSHOT:
            marketSnapshot(buffer);
            break;
        case MESSAGE_TYPE_TICKER:
            ticker.get(buffer);
            listener.ticker(ticker);
            break;
        default:
            throw new HotspotBookException("Unknown message type: " + (char)messageType);
        }
    }

    private void marketSnapshot(ByteBuffer buffer) throws IOException {
        listener.marketSnapshotStart();

        // Length of Message
        buffer.get(state.lengthOfMessage);

        long lengthOfMessage = ASCII.getLong(state.lengthOfMessage);

        if (lengthOfMessage < state.numberOfItems.length) {
            listener.marketSnapshotEnd();

            return;
        }

        // Number of Currency Pairs
        buffer.get(state.numberOfItems);

        long numberOfCurrencyPairs = ASCII.getLong(state.numberOfItems);

        for (int i = 0; i < numberOfCurrencyPairs; i++) {

            // Currency Pair
            buffer.get(marketSnapshotEntry.currencyPair);

            marketSnapshotEntry.buyOrSellIndicator = BUY;

            // Number of Bid Prices
            buffer.get(state.numberOfItems);

            long numberOfBidPrices = ASCII.getLong(state.numberOfItems);

            for (int j = 0; j < numberOfBidPrices; j++) {

                // Bid Price
                buffer.get(marketSnapshotEntry.price);

                // Number of Bid Orders
                buffer.get(state.numberOfItems);

                long numberOfBidOrders = ASCII.getLong(state.numberOfItems);

                for (int k = 0; k < numberOfBidOrders; k++) {
                    buffer.get(marketSnapshotEntry.amount);
                    buffer.get(marketSnapshotEntry.minqty);
                    buffer.get(marketSnapshotEntry.lotsize);
                    buffer.get(marketSnapshotEntry.orderId);

                    listener.marketSnapshotEntry(marketSnapshotEntry);
                }
            }

            marketSnapshotEntry.buyOrSellIndicator = SELL;

            // Number of Offer Prices
            buffer.get(state.numberOfItems);

            long numberOfOfferPrices = ASCII.getLong(state.numberOfItems);

            for (int j = 0; j < numberOfOfferPrices; j++) {

                // Offer Price
                buffer.get(marketSnapshotEntry.price);

                // Number of Offer Orders
                buffer.get(state.numberOfItems);

                long numberOfOfferOrders = ASCII.getLong(state.numberOfItems);

                for (int k = 0; k < numberOfOfferOrders; k++) {
                    buffer.get(marketSnapshotEntry.amount);
                    buffer.get(marketSnapshotEntry.minqty);
                    buffer.get(marketSnapshotEntry.lotsize);
                    buffer.get(marketSnapshotEntry.orderId);

                    listener.marketSnapshotEntry(marketSnapshotEntry);
                }
            }
        }

        listener.marketSnapshotEnd();
    }

    private static class State {
        public byte[] lengthOfMessage;
        public byte[] numberOfItems;

        public State() {
            lengthOfMessage = new byte[6];
            numberOfItems   = new byte[4];
        }
    }

}
