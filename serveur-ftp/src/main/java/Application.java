package main.java;

import main.java.server.Server;

public class Application {

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

}
