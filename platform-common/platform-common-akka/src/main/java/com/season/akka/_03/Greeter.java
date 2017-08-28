package com.season.akka._03;

import akka.actor.UntypedActor;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by yingchun on 2017/8/4.
 */
public class Greeter extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws InterruptedException {

        try {
            System.out.println("Greeter收到的数据为：" + JSONObject.toJSONString(msg));
            System.out.println("greeter getself " + getSelf());
            getSender().tell("Greeter工作完成。", getSelf());//给发送至发送信息.
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
