package com.car.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.car.akka.actor.Mapper;
import com.car.akka.actor.Master;
import com.car.akka.actor.Reducer;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static final int NB_REDUCER = 2;
    public static final int NB_MAPPER = 3;

    public static void main( String[] args ) {
        ActorSystem actorSystem = ActorSystem.create("mapReducerSystem");

        List<ActorRef> reducers = new ArrayList<>();
        for (int i=0 ; i<NB_REDUCER ; i++) {
            reducers.add(actorSystem.actorOf(Props.create(Reducer.class, "reducer"+i)));
        }

        List<ActorRef> mappers = new ArrayList<>();
        for (int i=0 ; i<NB_MAPPER ; i++) {
            mappers.add(actorSystem.actorOf(Props.create(Mapper.class, "mapper"+i, reducers)));
        }

        ActorRef master = actorSystem.actorOf(Props.create(Master.class, mappers));

        master.tell("test.txt", ActorRef.noSender());
    }

}
