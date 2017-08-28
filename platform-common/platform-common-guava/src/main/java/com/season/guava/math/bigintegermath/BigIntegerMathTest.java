package com.season.guava.math.bigintegermath;

import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @Author 熊彬
 * @Date 2017/7/25
 */
public class BigIntegerMathTest {

    /**
     * 除法
     */
    public static void testDivide(){
        System.out.println("1除以10，向远离零方向取整结果： " + BigIntegerMath.divide(new BigInteger("1"),new BigInteger("10"), RoundingMode.UP));
        System.out.println("1除以10，向零方向取整结果： " + BigIntegerMath.divide(new BigInteger("1"),new BigInteger("10"), RoundingMode.DOWN));
    }

    /**
     * 平方根
     */
    public static void testSqrt(){
        System.out.println("平方根100结果： " + BigIntegerMath.sqrt(new BigInteger("100"),RoundingMode.HALF_EVEN));
    }


    public static void main(String[] args) {
        BigIntegerMathTest.testDivide();
        BigIntegerMathTest.testSqrt();
    }
}
