package Networking;
/**
 * Creates a client object that can communicate with the server.
 * This class may be integrated into another file and eventually deleted
 * but for now it allows testing of the client and server.
 */

public class RunClient {

    private static Client client;

    public static void main(String[] args) {
//        String serverName = args[0];
//        int port = Integer.parseInt(args[1]);
        String serverName = "localhost";
        int port = 1234;

        System.out.println("Connecting to " + serverName + " on port " + port);

        client = new Client(serverName, port);
        client.connect();
//        client.disconnect();
    }

}
