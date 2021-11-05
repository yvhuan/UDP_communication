package com.yu.udpDemo;

import java.net.SocketException;

public class Marry {
    public static void main(String[] args) throws SocketException {
        new Thread(new Send(5555,"127.0.0.1",8850)).start();
        new Thread(new Receive(8851,"Judy")).start();
    }
}
