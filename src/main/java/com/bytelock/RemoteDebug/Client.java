package com.bytelock.RemoteDebug;

import com.bytelock.RemoteDebug.enums.ClientStatus;
import com.bytelock.RemoteDebug.exceptions.client.ClientNotConnected;
import com.bytelock.RemoteDebug.exceptions.client.DisconnectFailure;
import com.bytelock.RemoteDebug.exceptions.client.ReadConnectionStatusFailure;
import com.bytelock.RemoteDebug.packet.Packet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    /**
     * Current Connection Status for The Server
     */
    private ClientStatus clientStatus = ClientStatus.DISCONNECTED;
    public ClientStatus getServerStatus() {
        return clientStatus;
    }
    private Socket socket;

    public Client() { }

    /**
     * Connect to the remote server
     * @param host the server host you want to connect to
     * @param port the port the server is running on
     * @throws IOException is the exception thrown if we cannot connect to a host
     */
    public void connect(String host, int port) throws IOException {
        if(clientStatus == ClientStatus.DISCONNECTED) {
            socket = new Socket(host, port);
            while (socket.isConnected() == false) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clientStatus = ClientStatus.CONNECTED;
        }
    }

    /**
     * Connect to the remote server
     * @param address the server ip you want to connect to
     * @param port the port the server is running on
     * @throws IOException is the exception thrown if we cannot connect to a host
     */
    public void connect(InetAddress address, int port) throws IOException {
        if(clientStatus == ClientStatus.DISCONNECTED) {
            socket = new Socket(address, port);
            while (socket.isConnected() == false) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clientStatus = ClientStatus.CONNECTED;
        }
    }

    /**
     * Closes the socket that is connected to the server
     * @throws DisconnectFailure if we cannot properly disconnect from the remote server
     */
    public void disconnect() throws ClientNotConnected, DisconnectFailure {
        if(clientStatus.equals(ClientStatus.CONNECTED)) {
            try {
                socket.close();
                clientStatus = ClientStatus.DISCONNECTED;
            } catch (IOException ioException) {
                throw new DisconnectFailure("Could not disconnect from the remote server");
            }
        } else {
            throw new ClientNotConnected("Not currently connected to the remote server");
        }
    }

    /**
     * Write to the client socket
     * @param message the message you want to send as a string
     */
    public void write(String message) {
        try {

            if (clientStatus.equals(ClientStatus.CONNECTED) && socket.isConnected()) {
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream, true);
                printWriter.println(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Write to the client socket
     * @param packet the packet that you want to send
     */
    public void write(Packet packet) {
        try {
            if(clientStatus.equals(ClientStatus.CONNECTED) && socket.isConnected()) {
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(packet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads the socket stream for input
     * @return a line of text
     * @throws ReadConnectionStatusFailure
     */
    public String read() throws ReadConnectionStatusFailure {
        // Check if server is connected
        if(clientStatus != ClientStatus.CONNECTED && !socket.isConnected()) { return null; }

        // Create and read the input stream
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int character;
            StringBuilder data = new StringBuilder();
            while ((character = inputStreamReader.read()) != -1) {
                data.append((char) character);
            }
            return data.toString();
        } catch(IOException ex) {
            throw new ReadConnectionStatusFailure("Couldn't read the socket input stream");
        }

    }

    /**
     * Get the socket connection
     * @return the socket connection (THIS WILL BE TRUE INDEFINITELY IF THERE IS A CONNECTION
     */
    public boolean SocketConnected() {
        return socket.isConnected();
    }

    /**
     * Get the client status
     * @return the client status
     */
    public ClientStatus getClientStatus() {
        return clientStatus;
    }
}
