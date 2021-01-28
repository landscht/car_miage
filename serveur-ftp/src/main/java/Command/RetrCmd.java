package main.java.Command;

import main.java.message.MessageType;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class RetrCmd extends CommandTransfert {

    @Override
    void execTransfert() throws Exception {
        String oldName = getDirectoryName();
        String newName = getDirectoryNameTwoArgs();
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        oldFile.renameTo(newFile);
        DataOutputStream dataOutputStream = new DataOutputStream(this.session.getSocketPassif().getOutputStream());
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        byte[] buffer = new byte[this.session.getSocketPassif().getSendBufferSize()];
        int bytesRead = 0;
        while((bytesRead = fileInputStream.read(buffer))>0) {
            dataOutputStream.write(buffer,0,bytesRead);
        }
        fileInputStream.close();
        dataOutputStream.flush();
    }

}
