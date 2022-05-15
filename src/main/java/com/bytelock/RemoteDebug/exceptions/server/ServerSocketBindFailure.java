package com.bytelock.RemoteDebug.exceptions.server;

public class ServerSocketBindFailure extends Exception {
    public ServerSocketBindFailure(String message) {
        super(message);
    }
    public ServerSocketBindFailure() {
        super();
    }
}
