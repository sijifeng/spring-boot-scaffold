package com.season.akka._02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by yingchun on 2017/8/4.
 */
public class Main {
    public static void main(String[] args) {
        //akka.Main.main(new String[] { HelloWorld.class.getName() });
        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println(a.path());
    }
}
