package com.season.guava.math.doublemath;

import com.google.common.math.DoubleMath;

import java.math.RoundingMode;

/**
 * @Author 熊彬
 * @Date 2017/7/25
 */
public class DoubleMathTest {

    public static void testIsMathematicalInteger() {
        System.out.println("2是整数：" + DoubleMath.isMathematicalInteger(2));
        System.out.println("2.11是整数：" + DoubleMath.isMathematicalInteger(2.11));
    }

    public static void testRoundToInt() {
        try {
            DoubleMath.roundToInt(2.2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException e) {
            System.out.println("RoundingMode.UNNECESSARY如果有小数会抛出ArithmeticException异常：" + e.getMessage() + "\n");
        }
        System.out.println("2.2向负无穷方向取整：" + DoubleMath.roundToInt(2.2, RoundingMode.FLOOR) + "\n");

        System.out.println("2.2向正无穷方向取整：" + DoubleMath.roundToInt(2.2, RoundingMode.CEILING) + "\n");

        System.out.println("2.2向远离零方向取整返回int是：" + DoubleMath.roundToInt(2.2, RoundingMode.UP) + "\n");

        System.out.println("2.2向零方向取整返回int是：" + DoubleMath.roundToInt(2.2, RoundingMode.DOWN) + "\n");

        System.out.println("2.2小数部分小于0.5，执行RoundingMode.ROUND_HALF_DOWN是：" + DoubleMath.roundToInt(2.2, RoundingMode.HALF_UP) + "\n");
        System.out.println("2.5小数部分等于于0.5，执行RoundingMode.ROUND_HALF_DOWN是：" + DoubleMath.roundToInt(2.5, RoundingMode.HALF_UP) + "\n");
        System.out.println("2.6小数部分大于0.5，执行RoundingMode.ROUND_HALF_DOWN是：" + DoubleMath.roundToInt(2.6, RoundingMode.HALF_UP) + "\n");

        System.out.println("3.2小数部分小于0.5，执行RoundingMode.ROUND_HALF_UP是：" + DoubleMath.roundToInt(3.2, RoundingMode.HALF_DOWN) + "\n");
        System.out.println("3.5小数部分等于0.5，执行RoundingMode.ROUND_HALF_UP是：" + DoubleMath.roundToInt(3.5, RoundingMode.HALF_DOWN) + "\n");
        System.out.println("3.6小数部分大于0.5，执行RoundingMode.ROUND_HALF_UP是：" + DoubleMath.roundToInt(3.6, RoundingMode.HALF_DOWN) + "\n");

        System.out.println("2.2小数点左边是偶数，执行RoundingMode.ROUND_HALF_DOWN是：" + DoubleMath.roundToInt(2.2, RoundingMode.HALF_EVEN) + "\n");
        System.out.println("3.5小数点左边是奇数，执行RoundingMode.ROUND_HALF_UP是：" + DoubleMath.roundToInt(3.5, RoundingMode.HALF_EVEN) + "\n");
    }

    public static void testRoundToBigInteger() {
        System.out.println("2.2向远离零方向取整返回BigInteger是：" + DoubleMath.roundToBigInteger(2.2, RoundingMode.UP));
    }

    public static void testRoundToLong() {
        System.out.println("2.2向远离零方向取整返回long是：" + DoubleMath.roundToLong(2.2, RoundingMode.UP));
    }

    public static void main(String[] args) {
        DoubleMathTest.testIsMathematicalInteger();
        DoubleMathTest.testRoundToInt();
        DoubleMathTest.testRoundToLong();
        DoubleMathTest.testRoundToBigInteger();
    }
}
