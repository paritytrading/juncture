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
package com.paritytrading.juncture.cboe.fx.itch;

import static com.paritytrading.juncture.cboe.fx.itch.CboeFXBookEvents.*;
import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;

import com.paritytrading.foundation.ASCII;
import java.nio.ByteBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CboeFXBookTest {

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
            CboeFXBook.BUY,
            9500,
            100,
            0,
            0,
            "100            "
    );

    private static final MarketSnapshotEntry BID_2 = new MarketSnapshotEntry(
            "FOO/BAR",
            CboeFXBook.BUY,
            9500,
            50,
            0,
            0,
            "101            "
    );

    private static final MarketSnapshotEntry OFFER = new MarketSnapshotEntry(
            "FOO/BAR",
            CboeFXBook.SELL,
            10500,
            100,
            0,
            0,
            "200            "
    );

    private static final MarketSnapshotEntry OFFER_2 = new MarketSnapshotEntry(
            "FOO/BAR",
            CboeFXBook.SELL,
            11000,
            50,
            0,
            0,
            "201            "
    );

    private ByteBuffer buffer;

    private CboeFXBookEvents events;

    private CboeFXBookFormatter formatter;
    private CboeFXBookParser    parser;

    @BeforeEach
    void setUp() {
        buffer = ByteBuffer.allocateDirect(1024);

        events = new CboeFXBookEvents();

        formatter = new CboeFXBookFormatter();
        parser    = new CboeFXBookParser(events);
    }

    @Test
    void formatEmptySnapshot() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_EMPTY, remaining(buffer));
    }

    @Test
    void parseEmptySnapshot() throws Exception {
        parser.parse(wrap(SNAPSHOT_EMPTY));

        assertEquals(asList(START, END), events.collect());
    }

    @Test
    void formatSnapshotWithBid() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(BID));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_BID, remaining(buffer));
    }

    @Test
    void parseSnapshotWithBid() throws Exception {
        parser.parse(wrap(SNAPSHOT_BID));

        assertEquals(asList(START, BID, END), events.collect());
    }

    @Test
    void formatSnapshotWithOffer() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(OFFER));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_OFFER, remaining(buffer));
    }

    @Test
    void parseSnapshotWithOffer() throws Exception {
        parser.parse(wrap(SNAPSHOT_OFFER));

        assertEquals(asList(START, OFFER, END), events.collect());
    }

    @Test
    void formatSnapshotWithBidAndOffer() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(BID));
        formatter.marketSnapshotEntry(buffer, entry(OFFER));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_BID_OFFER, remaining(buffer));
    }

    @Test
    void parseSnapshotWithBidAndOffer() throws Exception {
        parser.parse(wrap(SNAPSHOT_BID_OFFER));

        assertEquals(asList(START, BID, OFFER, END), events.collect());
    }

    @Test
    void formatSnapshotWithBidAndBid2() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(BID));
        formatter.marketSnapshotEntry(buffer, entry(BID_2));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_BID_BID_2, remaining(buffer));
    }

    @Test
    void parseSnapshotWithBidAndBid2() throws Exception {
        parser.parse(wrap(SNAPSHOT_BID_BID_2));

        assertEquals(asList(START, BID, BID_2, END), events.collect());
    }

    @Test
    void formatSnapshotWithOfferAndOffer2() {
        formatter.marketSnapshotStart(buffer);
        formatter.marketSnapshotEntry(buffer, entry(OFFER));
        formatter.marketSnapshotEntry(buffer, entry(OFFER_2));
        formatter.marketSnapshotEnd(buffer);

        buffer.flip();

        assertEquals(SNAPSHOT_OFFER_OFFER_2, remaining(buffer));
    }

    @Test
    void parseSnapshotWithOfferAndOffer2() throws Exception {
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

    private CboeFXBook.MarketSnapshotEntry entry(MarketSnapshotEntry event) {
        CboeFXBook.MarketSnapshotEntry entry = new CboeFXBook.MarketSnapshotEntry();

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
