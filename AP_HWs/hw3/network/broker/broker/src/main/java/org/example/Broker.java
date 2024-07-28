package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Broker {
    public Broker(int port) {
        System.out.println("Starting Broker service...");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                connection.start();
            }
        } catch (IOException e) {
            //TODO: try to reconnect...
        }
    }
}
