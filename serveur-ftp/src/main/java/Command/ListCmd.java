package main.java.Command;

import main.java.message.MessageType;

import java.io.DataOutputStream;
import java.io.File;

public class ListCmd extends CommandTransfert {

    @Override
    void execTransfert() throws Exception {
        DataOutputStream dtpDataOutputStream = new DataOutputStream(this.session.getSocketPassif().getOutputStream());
        String dir = this.getDirectoryName();
        dtpDataOutputStream.write(createList(dir).getBytes());
        dtpDataOutputStream.flush();
    }

    private String createList(final String dir) {
        StringBuilder result = new StringBuilder();
        final File folder = new File(dir);
        if (!folder.exists() || !folder.isDirectory()) {
            return "Directory does not exist";
        }
        final File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];
            if (file.isDirectory()) {
                result.append("d ").append(file.getName()).append("\r\n");
            }else {
                result.append(file.getName()).append("\r\n");
            }
        }
        return result.toString();
    }

}
