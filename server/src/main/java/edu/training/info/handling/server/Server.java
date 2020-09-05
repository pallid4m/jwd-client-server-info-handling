package edu.training.info.handling.server;

import edu.training.info.handling.controller.TextController;
import edu.training.info.handling.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Server implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(Server.class);
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        Server server = new Server(55555);
        Thread thread = new Thread(server);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = server.accept();
                System.out.println(clientSocket + " is established");
                Client client = new Client(clientSocket);
                Thread thread = new Thread(client);
                thread.start();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
class Client implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(Client.class);
    private Socket clientSocket;

    private TextController controller = new TextController();

    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            String help = "todo help";
            out.writeObject(help);
            while (clientSocket.isConnected()) {
                String request = (String) in.readObject();
                System.out.println(clientSocket + ": " + request);
                Object response = controller.dispatch(request);
                out.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException | ServiceException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println(clientSocket + " is terminated");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
