package com.season.guava.base.joiner.mapjoiner;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/21 11:40
 * @描述：
 * @注意事项：
 */
public class MapJoinerEX {
    public static void appendTo() {
        Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("name", "doctor");
        map.put("sex", "man");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = mapJoiner.appendTo(stringBuilder, map);
        System.out.println(stringBuilder);
    }

    public static void join() {
        Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
        Map<String, String> map = Maps.newLinkedHashMap();
        map.put("name", "doctor");
        map.put("sex", "man");
        System.out.println(mapJoiner.join(map));
    }

    public static void main(String[] args) {
        MapJoinerEX.appendTo();
        MapJoinerEX.join();
    }
}
