package main.java.Command;

import main.java.message.MessageType;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class RetrCmd extends Command {

    @Override
    void exec() throws Exception {
        this.session.sendMessage(MessageType.MESSAGE_125);
        String arg = this.req.split(" ")[1];
        File file = new File(this.session.getDirectory() + "/" + arg);

        DataOutputStream dataOutputStream = new DataOutputStream(this.session.getSocketPassif().getOutputStream());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buffer = new byte[this.session.getSocketPassif().getSendBufferSize()];
        int bytesRead = 0;

        while((bytesRead = fileInputStream.read(buffer))>0)
        {
            dataOutputStream.write(buffer,0,bytesRead);
        }

        fileInputStream.close();
        dataOutputStream.flush();
        this.session.getSocketPassif().close();
        this.session.sendMessage(MessageType.MESSAGE_226);
    }

}
