package com.season.akka._02;

import akka.actor.UntypedActor;

/**
 * Created by yingchun on 2017/8/4.
 */
public class Greeter extends UntypedActor {

    public static enum Msg {
        GREET, DONE;
    }

    @Override
    public void onReceive(Object msg) throws InterruptedException {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            Thread.sleep(1000);
            getSender().tell(Msg.DONE, getSelf());
        } else
            unhandled(msg);
    }

}