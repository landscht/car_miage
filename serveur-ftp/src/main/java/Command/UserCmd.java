package main.java.Command;

import main.java.message.MessageType;
import main.java.user.UserConstant;

public class UserCmd extends Command {

    @Override
    void exec() throws Exception {
        String username = this.req.split(" ")[1];
        if (UserConstant.username.equals(username)) {
            this.session.sendMessage(MessageType.MESSAGE_331);
        }else{
            this.session.disconnect(MessageType.MESSAGE_550);
        }
    }

}
