package com.paritytrading.juncture.nasdaq.itch50;

import java.io.IOException;

/**
 * Indicates a protocol error while handling the NASDAQ TotalView-ITCH 5.0
 * protocol.
 */
public class ITCH50Exception extends IOException {

    /**
     * Construct an instance with the specified detail message.
     *
     * @param message the detail message
     */
    public ITCH50Exception(String message) {
        super(message);
    }

}
