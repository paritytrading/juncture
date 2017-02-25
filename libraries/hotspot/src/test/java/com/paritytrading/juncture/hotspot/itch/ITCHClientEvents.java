package com.paritytrading.juncture.hotspot.itch;

import com.paritytrading.juncture.hotspot.itch.ITCHSessionEvents.*;

import com.paritytrading.foundation.ASCII;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.jvirtanen.value.Value;

class ITCHClientEvents implements ITCHClientListener {

    private List<Event> events;

    public ITCHClientEvents() {
        this.events = new ArrayList<>();
    }

    public List<Event> collect() {
        return events;
    }

    @Override
    public void heartbeatTimeout(ITCHClient session) {
        events.add(new HeartbeatTimeout());
    }

    @Override
    public void loginAccepted(ITCHClient session, ITCH.LoginAccepted packet) {
        long sequenceNumber = ASCII.getLong(packet.sequenceNumber);

        events.add(new LoginAccepted(sequenceNumber));
    }

    @Override
    public void loginRejected(ITCHClient session, ITCH.LoginRejected packet) {
        String reason = ASCII.get(packet.reason);

        events.add(new LoginRejected(reason));
    }

    @Override
    public void sequencedData(ITCHClient session, ITCH.SequencedData header, ByteBuffer payload) {
        String time  = ASCII.get(header.time);
        byte[] bytes = new byte[payload.remaining()];

        payload.get(bytes);

        events.add(new SequencedData(time, bytes));
    }

    @Override
    public void endOfSession(ITCHClient session) {
        events.add(new EndOfSession());
    }

    @Override
    public void errorNotification(ITCHClient session, ITCH.ErrorNotification packet) {
        String errorExplanation = ASCII.get(packet.errorExplanation);

        events.add(new ErrorNotification(errorExplanation));
    }

    @Override
    public void instrumentDirectory(ITCHClient session, ITCH.InstrumentDirectory packet) {
        long numberOfCurrencyPairs = ASCII.getLong(packet.numberOfCurrencyPairs);

        List<String> currencyPairs = new ArrayList<>();

        for (int i = 0; i < numberOfCurrencyPairs; i++)
            currencyPairs.add(ASCII.get(packet.currencyPair[i]));

        events.add(new InstrumentDirectory(currencyPairs));
    }

    public interface Event {
    }

    public static class LoginAccepted extends Value implements Event {
        public final long sequenceNumber;

        public LoginAccepted(long sequenceNumber) {
            this.sequenceNumber = sequenceNumber;
        }
    }

    public static class LoginRejected extends Value implements Event {
        public final String reason;

        public LoginRejected(String reason) {
            this.reason = reason;
        }
    }

    public static class SequencedData extends Value implements Event {
        public final String time;
        public final byte[] payload;

        public SequencedData(String time, byte[] payload) {
            this.time    = time;
            this.payload = payload;
        }
    }

    public static class EndOfSession extends Value implements Event {
    }

    public static class ErrorNotification extends Value implements Event {
        public final String errorExplanation;

        public ErrorNotification(String errorExplanation) {
            this.errorExplanation = errorExplanation;
        }
    }

    public static class InstrumentDirectory extends Value implements Event {
        public final List<String> currencyPairs;

        public InstrumentDirectory(List<String> currencyPairs) {
            this.currencyPairs = currencyPairs;
        }
    }

}
