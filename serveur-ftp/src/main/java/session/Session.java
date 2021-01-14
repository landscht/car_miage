package main.java.session;

public class Session implements Runnable {

    @Override
    public void run() {
        System.out.println("Un client c'est connecté");
    }

}
