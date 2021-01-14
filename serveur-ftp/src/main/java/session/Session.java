package main.java.session;

import main.java.message.MessageType;
import main.java.user.UserConstant;

import java.io.*;
import java.net.Socket;

public class Session implements Runnable {

    /*
        Permet de recevoir les messages du client
     */
    private final BufferedReader in;

    /*
        Permet d'envoyer des messages au client
     */
    private final OutputStream out;

    /*
        Permet de gérer l'état du client
     */
    private final Socket socket;

    public Session(Socket serverSocket) throws IOException {
        this.socket = serverSocket;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = this.socket.getOutputStream();
    }

    @Override
    public void run()  {
        System.out.println("Un client s'est connecté");
        try {
            authentication();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void authentication() throws IOException {
        UserConstant userConstant = new UserConstant();
        sendMessage(MessageType.MESSAGE_220);
        String message = receiveMessage();
        String username = message.split(" ")[1];
        if (userConstant.goodUsername(username)) {
            sendMessage(MessageType.MESSAGE_331);
        }else{
            disconnect(MessageType.MESSAGE_550);
        }
        message = receiveMessage();
        String password = message.split(" ")[1];
        if (userConstant.goodPassword(password)) {
            sendMessage(MessageType.MESSAGE_230);
        }else {
            disconnect(MessageType.MESSAGE_550);
        }
        receiveMessage();
    }

    private void disconnect(String reason) throws IOException {
        sendMessage(reason);
        this.socket.close();
        System.out.println("Un client s'est deconnecté");
    }

    private void sendMessage(String message) throws IOException {
        out.write(message.getBytes());
        out.flush();
    }

    private String receiveMessage() throws IOException {
        String message = in.readLine();
        System.out.println(message);
        return message;
    }

}
