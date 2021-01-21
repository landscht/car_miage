package main.java.Command;

import main.java.message.MessageType;

public class QuitCmd extends Command {

    @Override
    void exec() throws Exception {
        this.session.disconnect(MessageType.MESSAGE_226);
    }

}
