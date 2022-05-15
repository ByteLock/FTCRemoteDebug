package com.bytelock.RemoteDebug.enums;

/**
 * The current status of the server
 */
public enum ServerStatus {
    /**
     * The server is accepting new clients
     */
    RUNNING,
    /**
     * The server is idle, not currently working
     */
    IDLE,
    /**
     * The server is starting
     */
    STARTING
}
