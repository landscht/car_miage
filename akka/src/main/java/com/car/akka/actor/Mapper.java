package com.car.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.car.akka.App;

import java.util.List;

public class Mapper extends UntypedActor {

    String name;
    List<ActorRef> reducers;

    public Mapper(String name, List<ActorRef> reducers) {
        this.name = name;
        this.reducers = reducers;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            readLine((String) message);
        }else {
            unhandled(message);
        }
    }

    public void readLine(String line) {
        line = line.replaceAll("[^a-zA-Z ]", "");
        System.out.println("[" + name + "] : " + line);
        String[] words = line.split(" ");
        for (String word: words) {
            reducers.get(partition(word)).tell(word, ActorRef.noSender());
        }
    }

    private int partition(String word) {
        if (word.hashCode() % App.NB_REDUCER < 0) {
            return 0;
        }
        return word.hashCode() % App.NB_REDUCER;
    }
}
