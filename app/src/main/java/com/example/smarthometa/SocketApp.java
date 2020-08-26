package com.example.smarthometa;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketApp {
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.100.5:3000");
        } catch (URISyntaxException e) {
            System.out.println(e.toString());
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}

