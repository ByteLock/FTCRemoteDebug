package Testing;

import com.bytelock.RemoteDebug.Client;
import com.bytelock.RemoteDebug.Server;
import com.bytelock.RemoteDebug.packet.Packet;

import java.util.Scanner;

public class ServerClientTest {

    private static boolean stayConnected = true;
    private static boolean run = true;
    public static void main(String[] args) {

        // Create a new remote client
        Client remoteClient = new Client();

        try {
            // start the remote server
            remoteServer.start();

            // while we are running
            while(run) {

                // connect to debug server (blocking code)
                remoteClient.connect("localhost", 8223);
                stayConnected = true;

                // while we want to our client to stay connected, keep taking input
                while (stayConnected) {
                    // Read input
                    Scanner input = new Scanner(System.in);

                    // Check for disconnect signal
                    if(input.findInLine("disconnect") == null) {

                        // Write a new packet to the client
                        Packet nP = new Packet("Test Heading", input.nextLine());

                        // Write to the debug client
                        if(remoteClient.SocketConnected()) {
                            remoteClient.write(nP);
                        }

                    } else {
                        if(input.findInLine("-f") != null) {
                            System.out.println(("Ending program"));
                            run = false;
                        }
                        // Disconnect
                        remoteClient.disconnect();

                        // Stop the while loop
                        stayConnected = false;
                    }
                }

            }
            remoteServer.stop();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }

    public static Server remoteServer = new Server(8223);
}
