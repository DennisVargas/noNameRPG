package Networking;

import java.io.IOException;
import java.net.*;


public class Client {

    private String ipAddress;
    private InetAddress serverAddress;
    private int port;
    private DatagramSocket socket;
    private boolean listening = false;


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

        byte[] connMsg = "conn".getBytes();
        send(connMsg);
        // wait for server to reply
        return true;
    }

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

}
