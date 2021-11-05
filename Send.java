package com.yu.udpDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Send implements Runnable {
    DatagramSocket socket = null;
    BufferedReader reader = null;

    private int myPort;
    private String toIP;
    private int toPort;

    public Send(int myPort, String toIP, int toPort) throws SocketException {
        this.myPort = myPort;
        this.toIP = toIP;
        this.toPort = toPort;

        socket = new DatagramSocket(this.myPort);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String data = null;
            try {
                data = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] sendData = data.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, 0, sendData.length, new InetSocketAddress(this.toIP, this.toPort));
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (data.trim().equals("bye")) {
                break;
            }

        }
        socket.close();
    }
}
