package com.season.akka._03;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;

/**
 * Created by yingchun on 2017/8/4.
 */
public class HelloWorld extends UntypedActor {

    ActorRef greeter;

    @Override
    public void preStart() {
        // create the greeter actor
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter actor path：" + greeter.path());
        // tell it to perform the greeting
        System.out.println(getSelf());
        greeter.tell(new Message(2, Arrays.asList("2", "dsf")), getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        try {
            System.out.println("HelloWorld收到的数据为：" + JSONObject.toJSONString(msg));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}