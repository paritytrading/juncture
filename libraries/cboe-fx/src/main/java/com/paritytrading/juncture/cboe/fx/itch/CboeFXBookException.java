package com.paritytrading.juncture.cboe.fx.itch;

import java.io.IOException;

/**
 * Indicates a protocol error while handling Cboe FX Book Protocol.
 */
public class CboeFXBookException extends IOException {

    /**
     * Create an instance with the specified detail message.
     *
     * @param message the detail message
     */
    public CboeFXBookException(String message) {
        super(message);
    }

}
