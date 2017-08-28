package com.season.guava.math.intmath;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * @Author 熊彬
 * @Date 2017/7/25
 */
public class IntMathTest {

    /**
     * 加法
     */
    public static void testCheckedAdd() {
        System.out.println("1加2等于：" + IntMath.checkedAdd(1, 2));
    }

    /**
     * 乘法
     */
    public static void testCheckedMultiply() {
        System.out.println("2乘以2等于：" + IntMath.checkedMultiply(2, 2));
    }

    /**
     * 减法
     */
    public static void testCheckedSubtract() {
        System.out.println("2减2等于：" + IntMath.checkedSubtract(2, 2));
    }

    /**
     * 除法
     */
    public static void testDivide() {
        System.out.println("2减2等于：" + IntMath.divide(2, 2, RoundingMode.UNNECESSARY));
    }

    /**
     * 平方根
     */
    public static void testSqrt() {
        System.out.println("平方根100结果： " + IntMath.sqrt(100, RoundingMode.UNNECESSARY));
    }

    public static void main(String[] args) {
        IntMathTest.testCheckedAdd();
        IntMathTest.testCheckedMultiply();
        IntMathTest.testCheckedSubtract();
        IntMathTest.testDivide();
        IntMathTest.testSqrt();
    }
}
