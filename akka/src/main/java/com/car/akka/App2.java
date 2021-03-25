package com.car.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.car.akka.actor.Mapper;
import com.car.akka.actor.Reducer;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.List;

public class App2 {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("app2", ConfigFactory.load("mapper"));

        List<ActorRef> reducers = new ArrayList<>();
        for (int i=0 ; i<App.NB_REDUCER ; i++) {
            reducers.add(actorSystem.actorOf(Props.create(Reducer.class, "reducer"+i)));
        }

        List<ActorRef> mappers = new ArrayList<>();
        for (int i=0 ; i<App.NB_MAPPER ; i++) {
            mappers.add(actorSystem.actorOf(Props.create(Mapper.class, "mapper"+i, reducers), "mapper" + i));
        }
    }

}
