package main.java.Command;

import main.java.message.MessageType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StorCmd extends Command {

    @Override
    void exec() throws Exception {
        this.session.sendMessage(MessageType.MESSAGE_125);

        InputStream dtpInputStream = this.session.getSocketPassif().getInputStream();
        String[] req = this.req.split(" ");
        File file = new File(req[1]);

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        byte[] buffer = new byte[this.session.getSocket().getReceiveBufferSize()];
        int bytesRead = 0;

        while ((bytesRead = dtpInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }

        fileOutputStream.close();
        fileOutputStream.flush();
        this.session.getSocketPassif().close();
        this.session.sendMessage(MessageType.MESSAGE_226);
    }

}
