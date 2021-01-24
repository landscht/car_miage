package main.java.Command;

import main.java.message.MessageType;

import java.net.Socket;
import java.util.Arrays;

public class PortCmd extends Command {

    @Override
    void exec() throws Exception {
        String[] addressAndPortArray = this.req.split(" ")[1].split(",");
        String address = String.join(".", Arrays.copyOf(addressAndPortArray,4));
        String port1 = addressAndPortArray[4];
        String port2 = addressAndPortArray[5];
        this.session.sendMessage(MessageType.MESSAGE_227.replace("p1", port1).replace("p2",port2));
        int port = Integer.parseInt(port1) * 256 + Integer.parseInt(port2);
        this.session.setSocketPassif(new Socket(address, port));
    }

}
