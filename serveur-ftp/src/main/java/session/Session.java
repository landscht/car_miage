package main.java.session;

import main.java.Command.Command;
import main.java.Command.CommandType;
import main.java.message.MessageType;
import main.java.server.Server;
import main.java.user.UserConstant;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Session implements Runnable {

    /*
        Permet de recevoir les messages du client
     */
    private final BufferedReader in;

    /*
        Permet d'envoyer des messages au client
     */
    private final OutputStream out;

    public Socket getSocket() {
        return socket;
    }

    /*
            Permet de gérer l'état du client
         */
    private final Socket socket;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    private String directory = ".";

    private final ServerSocket serverSocketPassif;

    public Socket getSocketPassif() {
        return socketPassif;
    }

    public void setSocketPassif(Socket socketPassif) {
        this.socketPassif = socketPassif;
    }

    private Socket socketPassif;

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

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
            sendMessage(MessageType.MESSAGE_220);
            this.inProcess = true;
            while (this.inProcess) {
                String message = receiveMessage();
                interpretCommand(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void interpretCommand(String message) throws Exception {
        if (message != null) {
            String cmd = message.split(" ")[0];
            if (CommandType.COMMAND.containsKey(cmd)) {
                Command command = CommandType.COMMAND.get(cmd);
                command.run(this, message);
            }else {
                sendMessage(MessageType.MESSAGE_502);
            }
        }
    }

    public void disconnect(String reason) throws IOException {
        sendMessage(reason);
        this.socket.close();
        this.inProcess = false;
        System.out.println("Un client s'est deconnecté");
    }

    public void sendMessage(String message) throws IOException {
        out.write(message.getBytes());
        out.flush();
    }

    private String receiveMessage() throws IOException {
        String message = in.readLine();
        System.out.println(message);
        return message;
    }
}
