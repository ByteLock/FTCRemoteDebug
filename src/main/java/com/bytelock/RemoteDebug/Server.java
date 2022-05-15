package com.bytelock.RemoteDebug;

import com.bytelock.RemoteDebug.enums.ServerStatus;
import com.bytelock.RemoteDebug.exceptions.server.ServerSocketBindFailure;
import com.bytelock.RemoteDebug.packet.Packet;
import com.sun.jdi.InternalException;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerStatus serverStatus;
    private ServerSocket serverSocket;
    private Boolean ClientConnected = false;
    private int ServerPort;

    /**
     * Initializer
     * @param port is the port you want to run the server on
     */
    public Server(int port) {
        serverStatus = ServerStatus.IDLE;
        this.ServerPort = port;
    }

    /**
     * Main server runtime
     */
    private Thread runtime = new Thread(() -> {
        while(serverStatus == ServerStatus.RUNNING) {
            try {
                // Accept the socket connection request (it will wait if there is none)
                Socket clientSocket = serverSocket.accept();
                System.out.println("server socket accepted");
                ClientConnected = true;
                // While we are connected, read
                while(ClientConnected) {
                    try {
                        // Create an input stream to read object from
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                        Packet input = (Packet) in.readObject();
                        System.out.println("Packet Heading: " + input.getPacketHeading());
                        System.out.println("Packet Tag: " + input.getPacketTag());
                        System.out.println("Packet Message: " + input.getPacketMessage());
                    } catch (EOFException ex) {
                        System.out.println("Never found eof, throwing away corrupted socket");
                        ClientConnected = false;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Couldn't accept server client");
            }
        }
    });

    /**
     * Start the server
     */
    public void start() throws ServerSocketBindFailure {
        // try to bind to port
        try {
            serverSocket = new ServerSocket(this.ServerPort);
        } catch (IOException e) {
            throw new ServerSocketBindFailure();
        }
        // if we are running, start the server runtime
        if(serverStatus != ServerStatus.RUNNING) {
            runtime.start();
            serverStatus = ServerStatus.RUNNING;
        }
    }

    /**
     * Stop the server
     */
    public void stop() {
        if(serverStatus == ServerStatus.RUNNING) {
            runtime.interrupt();
            System.out.println("interrupted");
            serverStatus = ServerStatus.IDLE;
        } else {
            System.out.println("server not running");
        }
    }

    /**
     * Stop the server
     * @param text
     */
    private void writeOutput(String text) {
        System.out.println(text);
        JOptionPane.showMessageDialog(null,
                text,
                "PopUp Dialog",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
