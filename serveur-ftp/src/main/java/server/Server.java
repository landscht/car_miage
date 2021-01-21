package main.java.server;

import main.java.session.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final static int PORT = 20;
    private final static int PORT_SENDER = 21;

    private boolean running = false;

    public Server() {}

    public void run() {
        System.out.println("Le serveur à démarrer sur le port " + PORT + "...");
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            ServerSocket serverSocketPassif = new ServerSocket(PORT_SENDER);
            System.out.println("Le serveur est prêt");
            this.running = true;
            while(this.running) {
                Socket socket = serverSocket.accept();
                new Thread(new Session(socket, serverSocketPassif)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du démarrage du serveur");
        }
    }

}
