package Networking;
/**
 * Creates a server object that can communicate with clents.
 * This class may be integrated into another file and eventually deleted
 * but for now it allows testing of the client and server.
 *
 * RunServer and RunClient both have main functions, so they currently run as independent programs.
 * Set up separate run configurations for each. Since this is UDP, there is no lasting connection so
 * the client program must be run every time it wants to send a package (will probably change later).
 */

public class RunServer {

    private static Server server;

    public static void main(String[] args) {
        System.out.println("Initializing Networking...");
        int port = 1234;
        server = new Server(port);
        server.start();
//        InetAddress address = null; // 127.0.0.1 (localhost)
//        try {
//            address = InetAddress.getByName("127.0.0.1");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        server.send(new byte[] {0, 1, 2}, address, port);
    }
}
