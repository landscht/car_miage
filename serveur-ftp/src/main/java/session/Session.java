package main.java.session;

import main.java.message.MessageType;
import main.java.server.Server;
import main.java.user.UserConstant;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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

    private final ServerSocket serverSocketPassif;

    private Socket socketPassif;

    private boolean inProcess;

    public Session(Socket serverSocket, ServerSocket serverSocketPassif) throws IOException {
        this.socket = serverSocket;
        this.serverSocketPassif = serverSocketPassif;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = this.socket.getOutputStream();
    }

    @Override
    public void run()  {
        System.out.println("Un client s'est connecté");
        try {
            authentication();
            while (this.inProcess) {
                String message = receiveMessage();
                interpret(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interpret(String message) throws IOException {
        if (message.equals("QUIT")) {
            disconnect(MessageType.MESSAGE_226);
        }else if(message.contains("STOR")) {
            putFile(message);
        }else if(message.contains("EPRT")) {
            eprtCmd(message);
        }else{
            sendMessage(MessageType.MESSAGE_502);
        }
    }

    private void putFile(String message) throws IOException {
        sendMessage(MessageType.MESSAGE_125);

        InputStream dtpInputStream = this.socketPassif.getInputStream();
        String[] req = message.split(" ");
        File file = new File(req[1]);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        byte[] buffer = new byte[socket.getReceiveBufferSize()];
        int bytesRead = 0;

        while ((bytesRead = dtpInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
        fileOutputStream.flush();
        this.socketPassif.close();
        sendMessage(MessageType.MESSAGE_226);
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
            this.inProcess = true;
            sendMessage(MessageType.MESSAGE_230);
        }else {
            disconnect(MessageType.MESSAGE_550);
        }
    }

    private void eprtCmd(String message) throws IOException {
        String[] req = message.split("\\|");
        int port = Integer.parseInt(req[req.length-1]);
        String addr = req[req.length-2];
        sendMessage(MessageType.MESSAGE_229.replace("port", req[req.length-1]));
        this.socketPassif = new Socket(addr, port);
    }

    private void disconnect(String reason) throws IOException {
        sendMessage(reason);
        this.socket.close();
        this.inProcess = false;
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
