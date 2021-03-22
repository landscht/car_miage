package com.car.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

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
        System.out.println("[" + name + "] : " + message);
    }
}
