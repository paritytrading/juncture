package com.paritytrading.juncture.hotspot.itch;

import java.io.IOException;

/**
 * Indicates a protocol error while handling Hotspot Book Protocol.
 */
public class HotspotBookException extends IOException {

    /**
     * Create an instance with the specified detail message.
     *
     * @param message the detail message
     */
    public HotspotBookException(String message) {
        super(message);
    }

}
