package Networking;

import java.io.IOException;
import java.net.*;


public class Server {
    private int port;
    private Thread listeningThread;
    private boolean listening = false;
    private DatagramSocket socket;
    private final int MAX_PACKET_SIZE = 1024;
    private byte[] dataBuffer = new byte[MAX_PACKET_SIZE*10];

    public Server(int port) {
        // set server port number
        this.port = port;
    }

    public void start() {
        // tro to open datagram socket, catch error if fails
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            return;
        }

        listening = true;

        // creates new thread to listen on so server can do other things while waiting
        listeningThread = new Thread(() -> listen(), "GameServerListener");
        listeningThread.start();
    }

    private void listen() {
        // listen while socket is open
        while(listening) {
            // create packet to hold received information
            DatagramPacket packet = new DatagramPacket(dataBuffer, MAX_PACKET_SIZE);

            // wait for packet to received
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("packet received");
            process(packet);
        }
    }

    private void process(DatagramPacket packet) {
        byte[] data = packet.getData();
        String infoTag = new String(data, 0, 4); // byte array, int offset, int length
        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();
        byte[] replyMsg;
//        System.out.println(infoTag);

        // each type of request needs a different tag to start with, so:
       switch(infoTag) {
            case "conn":
                System.out.println("Connection request received");
                replyMsg = "server: connection accepted".getBytes();
                send(replyMsg, clientAddress, clientPort);
                break;
            case "data":
                System.out.println("Data received from client");
                break;
           case "disc":
               System.out.println("Player disconnecting");
               replyMsg = "server: you have been disconnected from the server".getBytes();
               send(replyMsg, clientAddress, clientPort);
               break;
        }

    }

    public void send(byte[] data, InetAddress address, int port) {
        assert (socket.isConnected());

        // create data packet & address it
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

        // send datagram packet, catch error if fails
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server sent packet");
    }

}
