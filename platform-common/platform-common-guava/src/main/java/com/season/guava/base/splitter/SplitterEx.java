package com.season.guava.base.splitter;

import com.google.common.base.Splitter;

import java.util.Arrays;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/21 14:47
 * @描述：
 * @注意事项：
 */
public class SplitterEx {

    public static void splitterOld() {
        // 老的丢弃了尾部的,
        System.out.println(Arrays.asList(",a,,b,".split(",")));
    }

    public static void splitterNew() {
        Iterable iterable = Splitter.on(',').split("foo,bar");
        System.out.println(iterable);

        iterable = Splitter.on(',').split("foo,,bar, quux");
        System.out.println(iterable);

        Splitter MY_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
        iterable = MY_SPLITTER.split("foo,,bar, quux");
        System.out.println(iterable);

        // Bad! Do not do this!
        Splitter splitter = Splitter.on('/');
        splitter.trimResults(); // does nothing!
        System.out.println(splitter.split("wrong / wrong / wrong"));

    }

    public static void fixedLength() {
        Splitter splitter = Splitter.fixedLength(2);
        Iterable iterable = splitter.split("123456789qwertyuiopoasdfghjkllxcvbnmm");
        System.out.println(iterable);
    }

    public static void limit() {
        // 错误的写法
        Splitter splitter = Splitter.fixedLength(2);
        splitter.limit(10);
        Iterable iterable = splitter.split("123456789qwertyuiopoasdfghjkllxcvbnmm");
        System.out.println(iterable);

        //正确的写法
        splitter = Splitter.fixedLength(2).limit(10);
        iterable = splitter.split("123456789qwertyuiopoasdfghjkllxcvbnmm");
        System.out.println(iterable);
    }

    public static void omitEmptyStrings() {
        Splitter splitter = Splitter.on(",").omitEmptyStrings();
        Iterable iterable = splitter.split("1,,5,6,7,8,,,,uiosd,,ghjcvbnmm");
        System.out.println(iterable);
    }

    public static void main(String[] args) {
        SplitterEx.splitterOld();
        SplitterEx.splitterNew();
        SplitterEx.fixedLength();
        SplitterEx.limit();
        SplitterEx.omitEmptyStrings();
    }
}
