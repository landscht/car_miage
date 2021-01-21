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

    abstract void exec() throws Exception;

}
