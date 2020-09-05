package edu.training.info.handling.server;

import edu.training.info.handling.domain.bean.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Server implements Runnable {
    private int port;
    private Queue<Thread> queue = new LinkedList<>();
    private boolean run;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server(55555);
        Thread thread = new Thread(server);
        thread.start();
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            while (!scanner.next().equals("shutdown")) {}
            server.shutdown();
        }
    }

    public void shutdown() {
        run = false;
        for (Thread client : queue) {
            client.interrupt();
        }
        notify();
    }

    @Override
    public void run() {
        run = true;
        try (ServerSocket server = new ServerSocket(port)) {
            while (run) {
                Socket clientSocket = server.accept();
                System.out.println(clientSocket + " is established");
                Client client = new Client(clientSocket);
                Thread thread = new Thread(client);
                queue.add(thread);
                thread.start();
            }
            wait();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Client implements Runnable {
    private Socket clientSocket;

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            while (clientSocket.isConnected()) {
                User user = (User) ois.readObject();
                System.out.println(clientSocket + ": " + user);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println(clientSocket + " is terminated");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}