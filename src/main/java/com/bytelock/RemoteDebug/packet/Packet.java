package com.bytelock.RemoteDebug.packet;

import java.io.Serializable;

public class Packet implements Serializable {

    private String PacketHeading;
    private String PacketMessage;
    private String PacketTag;

    /**
     * Constructor including tag
     * @param PacketHeading The heading of the packet
     * @param PacketMessage The message of the packet
     * @param PacketTag The packet tag
     */
    public Packet(String PacketHeading, String PacketMessage, String PacketTag) {
        this.PacketHeading = PacketHeading;
        this.PacketMessage = PacketMessage;
        this.PacketTag = PacketTag;
    }

    /**
     * Default Constructor
     * @param PacketHeading The heading of the packet
     * @param PacketMessage The message of the packet
     */
    public Packet(String PacketHeading, String PacketMessage) {
        this.PacketHeading = PacketHeading;
        this.PacketMessage = PacketMessage;
    }

    /**
     * Get the packet heading
     * @return the packet heading
     */
    public String getPacketHeading() {
        return PacketHeading;
    }

    /**
     * Get the packet message
     * @return the packet message
     */
    public String getPacketMessage() {
        return PacketMessage;
    }

    /**
     * Get the packet tag
     * @return the packet tag
     */
    public String getPacketTag() {
        return PacketTag;
    }

    /**
     * Get the string version of this packet
     * @return the string version of this packet
     */
    public String toString() {
        return "[" + getPacketHeading() + "] [" + getPacketTag() + "] [" + getPacketMessage() + "]";
    }
}
