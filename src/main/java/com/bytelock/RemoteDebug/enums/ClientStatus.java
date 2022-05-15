package com.bytelock.RemoteDebug.enums;

/**
 * Status enums for the connection of the client
 */
public enum ClientStatus {
    /**
     * The client is connected to a host / remote server
     */
    CONNECTED,
    /**
     * The client is disconnected from a host / remote server | the client does not have an active socket connection
     */
    DISCONNECTED
}
