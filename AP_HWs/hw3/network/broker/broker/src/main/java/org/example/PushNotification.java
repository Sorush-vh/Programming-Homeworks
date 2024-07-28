package org.example;


import java.io.IOException;

public class PushNotification extends Thread {
    private final Connection connection;

    public PushNotification(Connection connection) {
        this.connection = connection;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (connection.subscribtionSet.contains(Database.recently_published_topic) && !Database.worked.contains(this)) {
                String packet = new Packet("push_notification", Database.recently_published_topic,
                        Database.getData(Database.recently_published_topic)).toJson();
                Database.worked.add(this);
                try {
                    connection.dataOutputStream.writeUTF(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
