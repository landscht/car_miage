package main.java.Command;

import main.java.message.MessageType;

import java.net.Socket;

public class EprtCmd extends Command {

    @Override
    void exec() throws Exception {
        String[] req = this.req.split("\\|");
        int port = Integer.parseInt(req[req.length-1]);
        String addr = req[req.length-2];
        this.session.sendMessage(MessageType.MESSAGE_229.replace("port", req[req.length-1]));
        this.session.setSocketPassif(new Socket(addr, port));
    }

}
