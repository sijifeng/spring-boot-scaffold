package com.season.guava.base.charmatcher;

import com.google.common.base.CharMatcher;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/24 9:28
 * @描述：
 * @注意事项：
 */
public class CharMatcherEX {

    public static void is() {
        // 返回匹配指定字符的Matcher
        CharMatcher charMatcher = CharMatcher.is('a');
        System.out.println(charMatcher.matchesAnyOf("bc"));
    }

    public static void isNot() {
        // 返回不匹配指定字符的Matcher
        CharMatcher charMatcher = CharMatcher.isNot('a');
        System.out.println(charMatcher.matchesAllOf("qwertyuioopdfghjkllzxcvbn"));
    }

    public static void anyOf() {
        // 返回匹配sequence中任意字符的Matcher
        CharMatcher charMatcher = CharMatcher.anyOf("i am king");
        System.out.println(charMatcher.matchesAnyOf("bcg"));
    }

    public static void noneOf() {
        // 返回不匹配sequence中任何一个字符的Matcher
        CharMatcher charMatcher = CharMatcher.noneOf("i am queue");
    }

    public static void inRange() {
        // 返回匹配范围内任意字符的Matcher
        CharMatcher charMatcher = CharMatcher.inRange('1', '9');
    }

    public static void forPredicate() {
        //返回使用predicate的apply()判断匹配的Matcher 看不明白 todo

    }

    public static void negate() {
        // 返回以当前Matcher判断规则相反的Matcher
        CharMatcher charMatcher = CharMatcher.inRange('1', '9').negate();
    }

    public static void and() {
        CharMatcher charMatcher = CharMatcher.inRange('1', '9');
        CharMatcher charMatcher2 = CharMatcher.inRange('a', 'z');
        charMatcher = charMatcher.and(charMatcher2);
    }

    public static void or() {
        CharMatcher charMatcher = CharMatcher.inRange('1', '9');
        CharMatcher charMatcher2 = CharMatcher.inRange('a', 'z');
        charMatcher = charMatcher.or(charMatcher2);
    }

    public static void matchesAnyOf() {
        CharMatcher charMatcher = CharMatcher.isNot('a');
        System.out.println(charMatcher.matchesAnyOf("abc"));
    }


    public static void main(String[] args) {
        CharMatcherEX.is();
        CharMatcherEX.isNot();
        CharMatcherEX.anyOf();
        CharMatcherEX.noneOf();
        CharMatcherEX.inRange();
        CharMatcherEX.forPredicate();
        CharMatcherEX.negate();
        CharMatcherEX.and();
        CharMatcherEX.or();
        CharMatcherEX.matchesAnyOf();
    }
}
