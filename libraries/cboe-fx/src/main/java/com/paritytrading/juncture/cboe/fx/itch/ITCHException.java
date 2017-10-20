package com.paritytrading.juncture.cboe.fx.itch;

import java.io.IOException;

/**
 * Indicates a protocol error while handling ITCH Session Management Protocol.
 */
public class ITCHException extends IOException {

    /**
     * Create an instance with the specified detail message.
     *
     * @param message the detail message
     */
    public ITCHException(String message) {
        super(message);
    }

}
