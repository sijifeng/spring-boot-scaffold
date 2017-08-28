package com.season.akka._03;

import java.util.Collections;
import java.util.List;

/**
 * Created by yingchun on 2017/8/4.
 * actor中传递的对象要是不可变对象。当然是为了安全了
 */
public final class Message {
    private final int age;
    private final List<String> list;

    public Message(int age, List<String> list) {
        this.age = age;
        /**
         * 把普通list包装为不可变对象
         */
        this.list = Collections.unmodifiableList(list);
    }

    public int getAge() {
        return age;
    }

    public List<String> getList() {
        return list;
    }
}
