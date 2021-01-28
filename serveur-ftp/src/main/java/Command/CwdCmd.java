package main.java.Command;

import main.java.message.MessageType;

import java.io.File;

public class CwdCmd extends Command {

    @Override
    void exec() throws Exception {
        String arg =  this.req.split(" ").length > 1 ? this.req.split(" ")[1] : "/";
        String dir = updateDirSession(arg);
        File file = new File(dir);
        if (!file.isDirectory() || !file.exists()) {
            this.session.sendMessage("Directory does not exist\r\n");
        }else {
            this.session.setDirectory(dir);
            this.session.sendMessage(MessageType.MESSAGE_200.replace("new", this.session.getDirectory()));
        }
    }

    private String updateDirSession(String dir){
        if(dir.charAt(0) == '/')
            return dir;
        else {
            if(this.session.getDirectory().charAt(this.session.getDirectory().length()-1) == '/')
                return this.session.getDirectory() + dir;
            else
                return this.session.getDirectory() + "/" + dir;
        }
    }

}
