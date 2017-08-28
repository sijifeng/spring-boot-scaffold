package com.season.guava.math.longmath;

import com.google.common.math.LongMath;

import java.math.RoundingMode;

/**
 * @Author 熊彬
 * @Date 2017/7/25
 */
public class LongMathTest {

    /**
     * 加法
     */
    public static void testCheckedAdd() {
        System.out.println("1加2等于：" + LongMath.checkedAdd(1, 2));
    }

    /**
     * 乘法
     */
    public static void testCheckedMultiply() {
        System.out.println("2乘以2等于：" + LongMath.checkedMultiply(2, 2));
    }

    /**
     * 减法
     */
    public static void testCheckedSubtract() {
        System.out.println("2减2等于：" + LongMath.checkedSubtract(2, 2));
    }

    /**
     * 除法
     */
    public static void testDivide() {
        System.out.println("2减2等于：" + LongMath.divide(2, 2, RoundingMode.UNNECESSARY));
    }

    /**
     * 平方根
     */
    public static void testSqrt() {
        System.out.println("平方根100结果： " + LongMath.sqrt(100, RoundingMode.UNNECESSARY));
    }
    
    public static void main(String[] args) {
        LongMathTest.testCheckedAdd();
        LongMathTest.testCheckedMultiply();
        LongMathTest.testCheckedSubtract();
        LongMathTest.testDivide();
        LongMathTest.testSqrt();
    }
}
