package main.java.Command;

import main.java.message.MessageType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StorCmd extends CommandTransfert {

    @Override
    void execTransfert() throws Exception {
        InputStream dtpInputStream = this.session.getSocketPassif().getInputStream();
        String[] req = this.req.split(" ");
        String str = req.length > 2 ? this.session.getDirectory() + "/" + req[2] : this.session.getDirectory() + "/" + req[1];
        File file = new File(str);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] buffer = new byte[this.session.getSocket().getReceiveBufferSize()];
        int bytesRead = 0;
        while ((bytesRead = dtpInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        fileOutputStream.close();
        fileOutputStream.flush();
    }

}
