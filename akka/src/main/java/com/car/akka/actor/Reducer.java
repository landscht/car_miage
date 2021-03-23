package com.car.akka.actor;

import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

public class Reducer extends UntypedActor {

    String name;
    Map<String, Integer> countWord = new HashMap<>();

    public Reducer(String name) {
        this.name = name;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof String) {
            String word = (String) message;
            if (countWord.containsKey(word)) {
                countWord.put(word, countWord.get(word) + 1);
            } else {
                countWord.put(word, 1);
            }
            System.out.println("[" + name + "]" + " : (" + word + ") -> " + countWord.get(word));
        }else {
            unhandled(message);
        }
    }

}
