package main.java.Command;

import main.java.session.Session;

public abstract class Command {

    protected Session session;
    protected String req;

    public void run(Session session, String req) throws Exception {
        this.session = session;
        this.req = req;
        exec();
    }

    protected String getDirectoryName(){
        String[] commands = this.req.split(" ");
        return commands.length > 1 ? this.session.getDirectory() + "/" + commands[1] : this.session.getDirectory();
    }

    abstract void exec() throws Exception;

}
