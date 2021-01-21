package main.java.Command;

import main.java.message.MessageType;
import main.java.session.Session;
import main.java.user.UserConstant;

import java.io.IOException;

public class PassCmd extends Command {

    @Override
    public void exec() throws IOException {
        String pwd = this.req.split(" ")[1];
        if (pwd.equals(UserConstant.password)) {
            this.session.setInProcess(true);
            this.session.sendMessage(MessageType.MESSAGE_230);
        }else {
            this.session.disconnect(MessageType.MESSAGE_550);
        }
    }

}
