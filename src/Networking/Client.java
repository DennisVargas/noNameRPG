package Networking;

import java.io.*;
import java.net.*;


public class Client {

    private String ipAddress;
    private InetAddress serverAddress;
    private int port;
//    private DatagramSocket socket;
    private boolean listening = false;
    Socket socket;


    // takes host in format "127.0.0.1:5000"
    public Client(String host) {
        String[] hostSplit = host.split(":");
        if (hostSplit.length != 2) {
            System.out.println("Invalid host string: Client constructor");
            return;
        }
        ipAddress = hostSplit[0];
        try {
            port = Integer.parseInt(hostSplit[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Invalid port: Client constructor");
        }

    }

    // takes address in form "127.0.0.1"
    public Client(String address, int port) {
        this.ipAddress = address;
        this.port = port;
    }

    public boolean connect() {
        // TCP
        try {
            socket = new Socket(ipAddress, port);
            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
//            out.writeUTF("Hello from " + socket.getLocalSocketAddress());
            out.writeUTF("INIT " + socket.getLocalSocketAddress() + " " + 1);
            listening = true;
            listen();
//            InputStream inFromServer = socket.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);
//            System.out.println("Server says " + in.readUTF());
//            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client cannot connect");
            return false;
        }

        // UDP
//        try {
//            serverAddress = InetAddress.getByName(ipAddress);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            return false;
//        }
//        try {
//            socket = new DatagramSocket(); // generates with random port number
//        } catch (SocketException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        byte[] connMsg = "conn".getBytes();
//        send(connMsg);
        // wait for server to reply
        return true;
    }

    private void listen() {
        // listen while socket is open
        while(listening) {
            // TCP
            try {
                InputStream inFromServer = socket.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("Server says: " + in.readUTF());
                send("Client: still connected");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Server has a problem listening");
                listening = false;
            }

            // UDP
            // create packet to hold received information
//            DatagramPacket packet = new DatagramPacket(dataBuffer, MAX_PACKET_SIZE);

            // wait for packet to received
//            try {
//                socket.receive(packet);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("packet received");
//            process(packet);
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void process() {
//
//    }


    private void send(String msg) {
        // send an outgoing message
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // UDP
    /*
    public boolean disconnect() {
        try {
            serverAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
        try {
            socket = new DatagramSocket(); // generates with random port number
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }

        byte[] connMsg = "disc".getBytes();
        send(connMsg);
        // wait for server to reply
        return true;
    }

    public void send(byte[] data) {
        assert (socket.isConnected());

        // create data packet & address it
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, port);

        // send datagram packet, catch error if fails
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client sent packet");
    }
*/
}
