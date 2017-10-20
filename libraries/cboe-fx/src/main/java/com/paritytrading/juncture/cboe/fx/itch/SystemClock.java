package com.paritytrading.juncture.cboe.fx.itch;

class SystemClock implements Clock {

    public static final SystemClock INSTANCE = new SystemClock();

    private SystemClock() {
    }

    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
