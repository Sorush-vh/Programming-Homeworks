package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Connection extends Thread {
    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    final Set<String> subscribtionSet = new HashSet<>();

    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public synchronized void run() {
        try {
            ClientType clientType;
            while ((clientType = get_intro()) == ClientType.UNKNOWN) ;
            if (clientType == ClientType.PUBLISHER) {
                while (true) {
                    handelPublisher();
                }
            } else {
                PushNotification pushNotification = new PushNotification(this);
                pushNotification.start();
                while (true) {
                    handelSubscriber();
                }
            }
        } catch (IOException e) {
            System.out.println("Connection " + socket.getInetAddress() + ":" + socket.getPort() + " lost!");
        }
    }

    private synchronized void handelSubscriber() throws IOException {
        if (dataInputStream.available() != 0) {
            String data = dataInputStream.readUTF();
            try {
                Packet packet = new Gson().fromJson(data, Packet.class);
                String topic = packet.topic;
                String value = packet.value;
                String command = packet.command;
                switch (command) {
                    case "add_subscription" -> {
                        if (Database.getData(topic) == null)
                            dataOutputStream.writeUTF("400: There is no topic with this name.");
                        else {
                            subscribtionSet.add(topic); //TODO: return an error message if the topic was added before
                            dataOutputStream.writeUTF("200: Topic added to your subscription list.");
                        }
                    }
                    case "delete_subscription" -> {
                        if (!subscribtionSet.contains(topic))
                            dataOutputStream.writeUTF("400: There is no subscription with this name.");
                        else {
                            subscribtionSet.remove(topic);
                            dataOutputStream.writeUTF("200: Topic removed from subscription list successfully.");
                        }
                    }
                    case "get_subscription_list" -> dataOutputStream.writeUTF(new Gson().toJson(subscribtionSet));
                    default -> dataOutputStream.writeUTF("400: Invalid command.");
                }
            } catch (JsonSyntaxException e) {
                dataOutputStream.writeUTF("400: Missing topic or command fields.");
            }
        }
    }

    private synchronized void handelPublisher() throws IOException {
        String data = dataInputStream.readUTF();
        try {
            // data = {"command": "danjkl", "topic": "adsf",  }
            Packet packet = new Gson().fromJson(data, Packet.class);
            String topic = packet.topic;
            String value = packet.value;
            String command = packet.command;
            switch (command) {
                case "new_topic" -> {
                    if (Database.createNewRecord(topic, value))
                        dataOutputStream.writeUTF("200: New topic created.");
                    else
                        dataOutputStream.writeUTF("400: Topic is duplicate.");
                }
                case "publish" -> {
                    if (Database.updateData(topic, value)) {
                        dataOutputStream.writeUTF("200: Data published successfully.");
                    } else
                        dataOutputStream.writeUTF("400: No such topic.");
                }
                default -> dataOutputStream.writeUTF("400: Invalid command.");
            }
        } catch (JsonSyntaxException e) {
            dataOutputStream.writeUTF("400: Missing topic or value or command fields.");
        }
    }

    private ClientType get_intro() throws IOException {
        String intro = dataInputStream.readUTF();
        switch (intro) {
            case "pub" -> {
                dataOutputStream.writeUTF("200: You are registered as a publisher client.");
                return ClientType.PUBLISHER;
            }
            case "sub" -> {
                dataOutputStream.writeUTF("200: You are registered as a subscriber client.");
                return ClientType.SUBSCRIBER;
            }
            default -> {
                dataOutputStream.writeUTF("400: Invalid intro");
                return ClientType.UNKNOWN;
            }
        }
    }
}
