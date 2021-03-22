package com.car.akka.actor;

import akka.actor.UntypedActor;

public class Reducer extends UntypedActor {

    String name;

    public Reducer(String name) {
        this.name = name;
    }

    @Override
    public void onReceive(Object message) throws Throwable {

    }

}
