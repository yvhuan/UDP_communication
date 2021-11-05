package com.yu.udpDemo;

import java.net.SocketException;

public class Jonh {
    public static void main(String[] args) throws SocketException {
        new Thread(new Send(7777,"127.0.0.1",8851)).start();
        new Thread(new Receive(8850,"Marry")).start();
    }
}
