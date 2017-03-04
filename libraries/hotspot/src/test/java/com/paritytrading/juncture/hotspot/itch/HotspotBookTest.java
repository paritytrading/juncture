package com.paritytrading.juncture.hotspot.itch;

import static com.paritytrading.juncture.hotspot.itch.HotspotBookEvents.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;

import com.paritytrading.foundation.ASCII;
import java.nio.ByteBuffer;
import org.junit.Before;
import org.junit.Test;

public class HotspotBookTest {

    private static final String SNAPSHOT_EMPTY = "" +
        "S" +     // Message Type
        "     0"; // Length of Message

    private static final String SNAPSHOT_BID = "" +
        "S"                + // Message Type
        "    96"           + // Length of Message
        "   1"             + // Number of Currency Pairs
        "FOO/BAR"          + // Currency Pair
        "   1"             + // Number of Bid Prices
        "0.9500    "       + // Bid Price
        "   1"             + // Number of Bid Orders
        "100             " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "100            "  + // Order ID
        "   0";              // Number of Offer Prices

    private static final String SNAPSHOT_OFFER = "" +
        "S"                + // Message Type
        "    96"           + // Length of Message
        "   1"             + // Number of Currency Pairs
        "FOO/BAR"          + // Currency Pair
        "   0"             + // Number of Bid Prices
        "   1"             + // Number of Offer Prices
        "1.0500    "       + // Offer Price
        "   1"             + // Number of Offer Orders
        "100             " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "200            ";   // Order ID

    private static final String SNAPSHOT_BID_OFFER = "" +
        "S"                + // Message Type
        "   173"           + // Length of Message
        "   1"             + // Number of Currency Pairs
        "FOO/BAR"          + // Currency Pair
        "   1"             + // Number of Bid Prices
        "0.9500    "       + // Bid Price
        "   1"             + // Number Of Bid Orders
        "100             " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "100            "  + // Order ID
        "   1"             + // Number of Offer Prices
        "1.0500    "       + // Offer Price
        "   1"             + // Number of Offer Orders
        "100             " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "200            ";   // Order ID

    private static final String SNAPSHOT_BID_BID_2 = "" +
        "S"                + // Message Type
        "   159"           + // Length of Message
        "   1"             + // Number of Currency Pairs
        "FOO/BAR"          + // Currency Pair
        "   1"             + // Number of Bid Prices
        "0.9500    "       + // Bid Price
        "   2"             + // Number of Bid Orders
        "100             " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "100            "  + // Order ID
        "50              " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "101            "  + // Order ID
        "   0";              // Number of Offer Prices

    private static final String SNAPSHOT_OFFER_OFFER_2 = "" +
        "S"                + // Message Type
        "   173"           + // Length of Message
        "   1"             + // Number of Currency Pairs
        "FOO/BAR"          + // Currency Pair
        "   0"             + // Number of Bid Prices
        "   2"             + // Number of Offer Prices
        "1.0500    "       + // Offer Price
        "   1"             + // Number of Offer Orders
        "100             " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "200            "  + // Order ID
        "1.1000    "       + // Offer Price
        "   1"             + // Number of Offer Orders
        "50              " + // Amount
        "0               " + // Minqty
        "0               " + // Lotsize
        "201            ";   // Order ID

    private static final Event START = new MarketSnapshotStart();

    private static final Event END = new MarketSnapshotEnd();

    private static final MarketSnapshotEntry BID = new MarketSnapshotEntry(
            "FOO/BAR",
            HotspotBook.BUY,
            9500,
            100,
            0,
            0,
            "100            "
    );

    private static final MarketSnapshotEntry BID_2 = new MarketSnapshotEntry(
            "FOO/BAR",
            HotspotBook.BUY,
            9500,
            50,
            0,
            0,
            "101            "
    );

    private static final MarketSnapshotEntry OFFER = new MarketSnapshotEntry(
            "FOO/BAR",
            HotspotBook.SELL,
            10500,
            100,
            0,
            0,
            "200            "
    );

    private static final MarketSnapshotEntry OFFER_2 = new MarketSnapshotEntry(
            "FOO/BAR",
            HotspotBook.SELL,
            11000,
            50,
            0,
            0,
            "201            "
    );

    private ByteBuffer buffer;

    private HotspotBookEvents events;

    private HotspotBookFormatter formatter;
    private HotspotBookParser    parser;

    @Before
    public void setUp() {
        buffer = ByteBuffer.allocateDirect(1024);

        events = new HotspotBookEvents();

        formatter = new HotspotBookFormatter();
        parser    = new HotspotBookParser(events);
    }

    @Test
    public void formatEmptySnapshot() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_EMPTY, remaining(buffer));
    }

    @Test
    public void parseEmptySnapshot() throws Exception {
        parser.parse(wrap(SNAPSHOT_EMPTY));

        assertEquals(asList(START, END), events.collect());
    }

    @Test
    public void formatSnapshotWithBid() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(BID));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_BID, remaining(buffer));
    }

    @Test
    public void parseSnapshotWithBid() throws Exception {
        parser.parse(wrap(SNAPSHOT_BID));

        assertEquals(asList(START, BID, END), events.collect());
    }

    @Test
    public void formatSnapshotWithOffer() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(OFFER));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_OFFER, remaining(buffer));
    }

    @Test
    public void parseSnapshotWithOffer() throws Exception {
        parser.parse(wrap(SNAPSHOT_OFFER));

        assertEquals(asList(START, OFFER, END), events.collect());
    }

    @Test
    public void formatSnapshotWithBidAndOffer() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(BID));
        formatter.marketSnapshotEntry(buffer, entry(OFFER));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_BID_OFFER, remaining(buffer));
    }

    @Test
    public void parseSnapshotWithBidAndOffer() throws Exception {
        parser.parse(wrap(SNAPSHOT_BID_OFFER));

        assertEquals(asList(START, BID, OFFER, END), events.collect());
    }

    @Test
    public void formatSnapshotWithBidAndBid2() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(BID));
        formatter.marketSnapshotEntry(buffer, entry(BID_2));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_BID_BID_2, remaining(buffer));
    }

    @Test
    public void parseSnapshotWithBidAndBid2() throws Exception {
        parser.parse(wrap(SNAPSHOT_BID_BID_2));

        assertEquals(asList(START, BID, BID_2, END), events.collect());
    }

    @Test
    public void formatSnapshotWithOfferAndOffer2() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(OFFER));
        formatter.marketSnapshotEntry(buffer, entry(OFFER_2));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_OFFER_OFFER_2, remaining(buffer));
    }

    @Test
    public void parseSnapshotWithOfferAndOffer2() throws Exception {
        parser.parse(wrap(SNAPSHOT_OFFER_OFFER_2));

        assertEquals(asList(START, OFFER, OFFER_2, END), events.collect());
    }

    private ByteBuffer wrap(String string) {
        return ByteBuffer.wrap(ASCII.put(string));
    }

    private String remaining(ByteBuffer buffer) {
        byte[] bytes = new byte[buffer.remaining()];

        buffer.get(bytes);

        return ASCII.get(bytes);
    }

    private HotspotBook.MarketSnapshotEntry entry(MarketSnapshotEntry event) {
        HotspotBook.MarketSnapshotEntry entry = new HotspotBook.MarketSnapshotEntry();

        ASCII.putLeft(entry.currencyPair, event.currencyPair);
        entry.buyOrSellIndicator = event.buyOrSellIndicator;
        ASCII.putFixedLeft(entry.price, event.price, PRICE_DECIMALS);
        ASCII.putLongLeft(entry.amount, event.amount);
        ASCII.putLongLeft(entry.minqty, event.minqty);
        ASCII.putLongLeft(entry.lotsize, event.lotsize);
        ASCII.putLeft(entry.orderId, event.orderId);

        return entry;
    }

}
