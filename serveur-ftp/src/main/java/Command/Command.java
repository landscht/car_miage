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
        String[] args = this.req.split(" ");
        if (args.length > 1 && args[1].charAt(0) == '/') {
            return args[1];
        }
        return args.length > 1 ? this.session.getDirectory() + "/" + args[1] : this.session.getDirectory();
    }

    protected String getDirectoryNameTwoArgs() {
        String[] args = this.req.split(" ");
        return args.length > 2 ? this.session.getDirectory() + "/" + args[2] : this.session.getDirectory() + "/" + args[1];
    }

    abstract void exec() throws Exception;

}
