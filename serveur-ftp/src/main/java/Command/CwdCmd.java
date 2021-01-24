package main.java.Command;

import main.java.message.MessageType;

public class CwdCmd extends Command {

    @Override
    void exec() throws Exception {
        String arg = this.req.split(" ")[1];
        if (arg.charAt(0) == '/') {
            this.session.setDirectory(arg);
        } else {
            this.session.setDirectory(this.session.getDirectory() + "/" + arg);
        }
        this.session.sendMessage(MessageType.MESSAGE_200.replace("new", this.session.getDirectory()));
    }

}
