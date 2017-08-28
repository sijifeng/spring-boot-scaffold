package com.season.guava.base.basicutilities.check;

import com.google.common.base.Preconditions;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/25 13:39
 * @描述：
 * @注意事项：
 */
public class CheckEX {

    public static void checkArgument() {
        Preconditions.checkArgument(1 == 2);
    }

    public static void checkNotNull() {
        String str = null;
        Preconditions.checkNotNull(str);
    }

    public static void checkState() {
        Preconditions.checkState(1 == 2);
    }

    public static void checkElementIndex() {
        Preconditions.checkElementIndex(2, 2);
    }

    public static void checkPositionIndex() {
        Preconditions.checkPositionIndex(2, 2);
    }

    public static void checkPositionIndexes() {
        Preconditions.checkPositionIndexes(0, 5, 5);
    }

    public static void main(String[] args) {
        //CheckEX.checkArgument();
        //CheckEX.checkNotNull();
        //CheckEX.checkState();
        //CheckEX.checkElementIndex();
        //CheckEX.checkPositionIndex();
        CheckEX.checkPositionIndexes();
    }
}
