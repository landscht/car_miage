package main.java.Command;

import main.java.message.MessageType;

import java.io.DataOutputStream;
import java.io.File;

public class ListCmd extends Command {

    @Override
    void exec() throws Exception {
        this.session.sendMessage(MessageType.MESSAGE_125);
        DataOutputStream dtpDataOutputStream = new DataOutputStream(this.session.getSocketPassif().getOutputStream());
        String dir = this.getDirectoryName();
        dtpDataOutputStream.write(createList(dir).getBytes());
        dtpDataOutputStream.flush();
        this.session.sendMessage(MessageType.MESSAGE_226);
        this.session.getSocketPassif().close();
    }

    private String createList(final String dir) {
        String result = "";
        final File folder = new File(dir);
        if (!folder.exists() || !folder.isDirectory()) {
            return "Directory does not exist";
        }
        final File[] files = folder.listFiles();
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];
            result += file.getName()+"\r\n";
        }
        return result;
    }

}
