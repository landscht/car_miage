package main.java.Command;

import main.java.message.MessageType;

public abstract class CommandTransfert extends Command {

    @Override
    void exec() throws Exception {
        this.session.sendMessage(MessageType.MESSAGE_125);
        execTransfert();
        this.session.getSocketPassif().close();
        this.session.sendMessage(MessageType.MESSAGE_226);
    }

    abstract void execTransfert() throws Exception;
}
