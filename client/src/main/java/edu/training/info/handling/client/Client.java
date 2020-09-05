package edu.training.info.handling.client;

import edu.training.info.handling.domain.bean.User;
import edu.training.info.handling.service.ServiceFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Client implements Runnable {
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 55555);
        Thread thread = new Thread(client);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (Socket hostSocket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(hostSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(hostSocket.getInputStream());
             Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            String string = scanner.next();
            while (!string.equals("quite")) {
                User user = new User(1, string);
                out.writeObject(user);
                string = scanner.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
