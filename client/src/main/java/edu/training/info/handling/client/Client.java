package edu.training.info.handling.client;

import edu.training.info.handling.domain.bean.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(Client.class);
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
            logger.error(e.getMessage());
        }
    }

    @Override
    public void run() {
        try (Socket hostSocket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(hostSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(hostSocket.getInputStream())) {
            String help = (String) in.readObject();
            System.out.println(help);
            String string;
            while ((string = System.console().readLine()) != null) {
                out.writeObject(string);
                Text text = (Text) in.readObject();
                System.out.println(text.view());
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }
}
