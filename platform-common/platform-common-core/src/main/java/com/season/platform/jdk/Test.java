package com.season.platform.jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingchun on 2017/7/23.
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.foreach();
    }

    public void foreach(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        list.forEach(x -> {
            System.out.println(x);
        });
    }
}
