package com.car.akka.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import com.car.akka.App;

import java.io.*;

public class Master extends UntypedActor {

    private final String URL_MAPPER = "akka.tcp://app2@127.0.0.1:3004/user/mapper";

    public Master() {
    }

    @Override
    public void onReceive(Object message) throws IOException {
        System.out.println(message);
        if (message instanceof String) {
            File file = new File((String) message);
            readFile(file);
        } else {
          unhandled(message);
        }
    }

    private void readFile(File file) throws IOException {
        BufferedReader lecteurAvecBuffer = new BufferedReader(new FileReader(file));
        String line;
        int nbLine = 0;
        while ((line = lecteurAvecBuffer.readLine()) != null) {
            int index = nbLine % App.NB_MAPPER;
            ActorSelection mapper = getContext().actorSelection(URL_MAPPER + index);
            mapper.tell(line, ActorRef.noSender());
            nbLine++;
        }
    }

}
