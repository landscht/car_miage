package com.car.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.car.akka.App;

import java.io.*;
import java.util.List;

public class Master extends UntypedActor {

    List<ActorRef> mappers;

    public Master(List<ActorRef> mappers) {
        this.mappers = mappers;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
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
            mappers.get(nbLine % App.NB_MAPPER).tell(line, ActorRef.noSender());
            nbLine++;
        }
    }

}
