package Networking;
/**
 * Creates a client object that can communicate with the server.
 * This class may be integrated into another file and eventually deleted
 * but for now it allows testing of the client and server.
 */

public class RunClient {

    private static Client client;

    public static void main(String[] args) {

        System.out.println("Initializing Client...");
        client = new Client("localhost", 1234);
        client.connect();
        client.disconnect();
    }

}
