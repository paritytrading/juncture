package com.paritytrading.juncture.cboe.fx.itch;

import org.jvirtanen.value.Value;

class ITCHSessionEvents {

    public static class HeartbeatTimeout extends Value
            implements ITCHClientEvents.Event, ITCHServerEvents.Event {
    }

}
