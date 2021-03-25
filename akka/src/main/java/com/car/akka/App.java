package com.car.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.car.akka.actor.Mapper;
import com.car.akka.actor.Master;
import com.car.akka.actor.Reducer;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static final int NB_REDUCER = 2;
    public static final int NB_MAPPER = 3;

    public static void main( String[] args ) {
        ActorSystem actorSystem = ActorSystem.create("app1", ConfigFactory.load("application"));

        ActorRef master = actorSystem.actorOf(Props.create(Master.class));

        master.tell("test-perso.txt", ActorRef.noSender());
    }

}
