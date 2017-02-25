package com.paritytrading.juncture.hotspot.itch;

import com.paritytrading.juncture.hotspot.itch.ITCHSessionEvents.*;

import com.paritytrading.foundation.ASCII;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.jvirtanen.value.Value;

class ITCHServerEvents implements ITCHServerListener {

    private List<Event> events;

    public ITCHServerEvents() {
        this.events = new ArrayList<>();
    }

    public List<Event> collect() {
        return events;
    }

    @Override
    public void heartbeatTimeout(ITCHServer session) {
        events.add(new HeartbeatTimeout());
    }

    @Override
    public void loginRequest(ITCHServer session, ITCH.LoginRequest packet) {
        String loginName             = ASCII.get(packet.loginName);
        String password              = ASCII.get(packet.password);
        byte   marketDataUnsubscribe = packet.marketDataUnsubscribe;
        long   reserved              = ASCII.getLong(packet.reserved);

        events.add(new LoginRequest(loginName, password, marketDataUnsubscribe, reserved));
    }

    @Override
    public void logoutRequest(ITCHServer session) {
        events.add(new LogoutRequest());
    }

    @Override
    public void marketSnapshotRequest(ITCHServer session, ITCH.MarketSnapshotRequest packet) {
        String currencyPair = ASCII.get(packet.currencyPair);

        events.add(new MarketSnapshotRequest(currencyPair));
    }

    @Override
    public void tickerSubscribeRequest(ITCHServer session, ITCH.TickerSubscribeRequest packet) {
        String currencyPair = ASCII.get(packet.currencyPair);

        events.add(new TickerSubscribeRequest(currencyPair));
    }

    @Override
    public void tickerUnsubscribeRequest(ITCHServer session, ITCH.TickerUnsubscribeRequest packet) {
        String currencyPair = ASCII.get(packet.currencyPair);

        events.add(new TickerUnsubscribeRequest(currencyPair));
    }

    @Override
    public void marketDataSubscribeRequest(ITCHServer session, ITCH.MarketDataSubscribeRequest packet) {
        String currencyPair = ASCII.get(packet.currencyPair);

        events.add(new MarketDataSubscribeRequest(currencyPair));
    }

    @Override
    public void marketDataUnsubscribeRequest(ITCHServer session, ITCH.MarketDataUnsubscribeRequest packet) {
        String currencyPair = ASCII.get(packet.currencyPair);

        events.add(new MarketDataUnsubscribeRequest(currencyPair));
    }

    @Override
    public void instrumentDirectoryRequest(ITCHServer session) {
        events.add(new InstrumentDirectoryRequest());
    }

    public interface Event {
    }

    public static class LoginRequest extends Value implements Event {
        public final String loginName;
        public final String password;
        public final byte   marketDataUnsubscribe;
        public final long   reserved;

        public LoginRequest(String loginName, String password, byte marketDataUnsubscribe,
                long reserved) {
            this.loginName             = loginName;
            this.password              = password;
            this.marketDataUnsubscribe = marketDataUnsubscribe;
            this.reserved              = reserved;
        }
    }

    public static class LogoutRequest extends Value implements Event {
    }

    public static class MarketSnapshotRequest extends Value implements Event {
        public final String currencyPair;

        public MarketSnapshotRequest(String currencyPair) {
            this.currencyPair = currencyPair;
        }
    }

    public static class TickerSubscribeRequest extends Value implements Event {
        public final String currencyPair;

        public TickerSubscribeRequest(String currencyPair) {
            this.currencyPair = currencyPair;
        }
    }

    public static class TickerUnsubscribeRequest extends Value implements Event {
        public final String currencyPair;

        public TickerUnsubscribeRequest(String currencyPair) {
            this.currencyPair = currencyPair;
        }
    }

    public static class MarketDataSubscribeRequest extends Value implements Event {
        public final String currencyPair;

        public MarketDataSubscribeRequest(String currencyPair) {
            this.currencyPair = currencyPair;
        }
    }

    public static class MarketDataUnsubscribeRequest extends Value implements Event {
        public final String currencyPair;

        public MarketDataUnsubscribeRequest(String currencyPair) {
            this.currencyPair = currencyPair;
        }
    }

    public static class InstrumentDirectoryRequest extends Value implements Event {
    }

}
