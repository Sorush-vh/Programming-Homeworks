package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;
    private ClientType clientType = ClientType.UNKNOWN;


    public Client(String host, int port) throws IOException {
        System.out.println("Starting Client service...");
        System.out.println("please specify your client type: [sub/pub]");
        Scanner scanner = new Scanner(System.in);
        while (clientType == ClientType.UNKNOWN) {
            String str = scanner.nextLine();
            if (str.equals("sub"))
                clientType = ClientType.SUBSCRIBER;
            else if (str.equals("pub"))
                clientType = ClientType.PUBLISHER;
            else
                System.out.println("Invalid client type.");
        }
        System.out.println(clientType + " type was set for your client.");
        Socket socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        if (clientType == ClientType.PUBLISHER) {
            dataOutputStream.writeUTF("pub");
            System.out.println(dataInputStream.readUTF());
            System.out.println("Enter Your request in this order: [command topic value]");
            while (true)
                handelPublisher(scanner);
        } else {
            dataOutputStream.writeUTF("sub");
            System.out.println(dataInputStream.readUTF());
            NotificationReceiver notificationReceiver = new NotificationReceiver(dataInputStream);
            notificationReceiver.start();
            System.out.println("Enter Your request in this order: [command topic]");
            while (true)
                handelSubscriber(scanner);
        }
    }

    private synchronized void handelSubscriber(Scanner scanner) throws IOException {
        String[] params = scanner.nextLine().split("\\s");
        if (params.length != 2)
            return;
        String packet = new Packet(params[0], params[1], "").toJson();
        dataOutputStream.writeUTF(packet);
    }

    private void handelPublisher(Scanner scanner) throws IOException {
        String[] params = scanner.nextLine().split("\\s");
        if (params.length != 3)
            return;
        String packet = new Packet(params[0], params[1], params[2]).toJson();
        dataOutputStream.writeUTF(packet);
        System.out.println(dataInputStream.readUTF());
    }
}
