package com.paritytrading.juncture.cboe.fx.itch;

import com.paritytrading.foundation.ASCII;
import java.util.ArrayList;
import java.util.List;
import org.jvirtanen.value.Value;

class CboeFXBookEvents implements CboeFXBookListener {

    public static final int PRICE_DECIMALS = 4;

    private List<Event> events;

    public CboeFXBookEvents() {
        events = new ArrayList<Event>();
    }

    public List<Event> collect() {
        return events;
    }

    @Override
    public void newOrder(CboeFXBook.NewOrder message) {
        byte   buyOrSellIndicator = message.buyOrSellIndicator;
        String currencyPair       = ASCII.get(message.currencyPair);
        String orderId            = ASCII.get(message.orderId);
        long   price              = ASCII.getFixed(message.price, PRICE_DECIMALS);
        long   amount             = ASCII.getLong(message.amount);
        long   minqty             = ASCII.getLong(message.minqty);
        long   lotsize            = ASCII.getLong(message.lotsize);

        events.add(new NewOrder(buyOrSellIndicator, currencyPair, orderId,
                    price, amount, minqty, lotsize));
    }

    @Override
    public void modifyOrder(CboeFXBook.ModifyOrder message) {
        String currencyPair = ASCII.get(message.currencyPair);
        String orderId      = ASCII.get(message.orderId);
        long   amount       = ASCII.getLong(message.amount);
        long   minqty       = ASCII.getLong(message.minqty);
        long   lotsize      = ASCII.getLong(message.lotsize);

        events.add(new ModifyOrder(currencyPair, orderId, amount,
                    minqty, lotsize));
    }

    @Override
    public void cancelOrder(CboeFXBook.CancelOrder message) {
        String currencyPair = ASCII.get(message.currencyPair);
        String orderId      = ASCII.get(message.orderId);

        events.add(new CancelOrder(currencyPair, orderId));
    }

    @Override
    public void marketSnapshotStart() {
        events.add(new MarketSnapshotStart());
    }

    @Override
    public void marketSnapshotEntry(CboeFXBook.MarketSnapshotEntry entry) {
        String currencyPair       = ASCII.get(entry.currencyPair);
        byte   buyOrSellIndicator = entry.buyOrSellIndicator;
        long   price              = ASCII.getFixed(entry.price, PRICE_DECIMALS);
        long   amount             = ASCII.getLong(entry.amount);
        long   minqty             = ASCII.getLong(entry.minqty);
        long   lotsize            = ASCII.getLong(entry.lotsize);
        String orderId            = ASCII.get(entry.orderId);

        events.add(new MarketSnapshotEntry(currencyPair, buyOrSellIndicator,
                    price, amount, minqty, lotsize, orderId));
    }

    @Override
    public void marketSnapshotEnd() {
        events.add(new MarketSnapshotEnd());
    }

    @Override
    public void ticker(CboeFXBook.Ticker message) {
        byte   aggressorBuyOrSellIndicator = message.aggressorBuyOrSellIndicator;
        String currencyPair                = ASCII.get(message.currencyPair);
        long   price                       = ASCII.getFixed(message.price, PRICE_DECIMALS);
        long   transactionDate             = ASCII.getLong(message.transactionDate);
        long   transactionTime             = ASCII.getLong(message.transactionTime);

        events.add(new Ticker(aggressorBuyOrSellIndicator, currencyPair,
                    price, transactionDate, transactionTime));
    }

    public interface Event {
    }

    public static class NewOrder extends Value implements Event {
        public byte   buyOrSellIndicator;
        public String currencyPair;
        public String orderId;
        public long   price;
        public long   amount;
        public long   minqty;
        public long   lotsize;

        public NewOrder(byte buyOrSellIndicator, String currencyPair,
                String orderId, long price, long amount, long minqty,
                long lotsize) {
            this.buyOrSellIndicator = buyOrSellIndicator;
            this.currencyPair       = currencyPair;
            this.orderId            = orderId;
            this.price              = price;
            this.amount             = amount;
            this.minqty             = minqty;
            this.lotsize            = lotsize;
        }
    }

    public static class ModifyOrder extends Value implements Event {
        public String currencyPair;
        public String orderId;
        public long   amount;
        public long   minqty;
        public long   lotsize;

        public ModifyOrder(String currencyPair, String orderId, long amount,
                long minqty, long lotsize) {
            this.currencyPair = currencyPair;
            this.orderId      = orderId;
            this.amount       = amount;
            this.minqty       = minqty;
            this.lotsize      = lotsize;
        }
    }

    public static class CancelOrder extends Value implements Event {
        public String currencyPair;
        public String orderId;

        public CancelOrder(String currencyPair, String orderId) {
            this.currencyPair = currencyPair;
            this.orderId      = orderId;
        }
    }

    public static class MarketSnapshotStart extends Value implements Event {
    }

    public static class MarketSnapshotEntry extends Value implements Event {
        public String currencyPair;
        public byte   buyOrSellIndicator;
        public long   price;
        public long   amount;
        public long   minqty;
        public long   lotsize;
        public String orderId;

        public MarketSnapshotEntry(String currencyPair, byte buyOrSellIndicator,
                long price, long amount, long minqty, long lotsize,
                String orderId) {
            this.currencyPair       = currencyPair;
            this.buyOrSellIndicator = buyOrSellIndicator;
            this.price              = price;
            this.amount             = amount;
            this.minqty             = minqty;
            this.lotsize            = lotsize;
            this.orderId            = orderId;
        }
    }

    public static class MarketSnapshotEnd extends Value implements Event {
    }

    public static class Ticker extends Value implements Event {
        public final byte   aggressorBuyOrSellIndicator;
        public final String currencyPair;
        public final long   price;
        public final long   transactionDate;
        public final long   transactionTime;

        public Ticker(byte aggressorBuyOrSellIndicator, String currencyPair,
                long price, long transactionDate, long transactionTime) {
            this.aggressorBuyOrSellIndicator = aggressorBuyOrSellIndicator;
            this.currencyPair                = currencyPair;
            this.price                       = price;
            this.transactionDate             = transactionDate;
            this.transactionTime             = transactionTime;
        }
    }


}
