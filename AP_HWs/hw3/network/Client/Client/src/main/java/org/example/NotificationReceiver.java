package org.example;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.IOException;

public class NotificationReceiver extends Thread {
    private final DataInputStream dataInputStream;

    public NotificationReceiver(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                String data = dataInputStream.readUTF();
                if(!data.startsWith("{"))
                    System.out.println(data);
                else {
                    Packet packet = new Gson().fromJson(data, Packet.class);
                    if (packet.command.equals("push_notification"))
                        System.out.println("New Push notification received! => " + packet.topic + " value is updated to " + packet.value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
