package com.yu.udpDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receive implements Runnable {
    DatagramSocket socket = null;
    private int port;
    private String receiveName;

    public Receive(int port, String receiveName) throws SocketException {
        this.port = port;
        this.receiveName = receiveName;
        socket = new DatagramSocket(this.port);
    }

    @Override
    public void run() {
        while (true) {
            //准备接受包裹
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
            try {
                socket.receive(packet); //诸塞式接受包裹
            } catch (IOException e) {
                e.printStackTrace();
            }

            //打印接受的信息
            byte[] data = packet.getData();
            String receiveMessage = new String(data, 0, packet.getLength());
            System.out.println(receiveName + "：" + receiveMessage);

            //断开，bye
            if (receiveMessage.equals("bye")) {
                break;
            }
        }
        socket.close();

    }
}
