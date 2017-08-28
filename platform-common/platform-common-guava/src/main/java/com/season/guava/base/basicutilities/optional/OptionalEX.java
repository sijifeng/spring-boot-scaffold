package com.season.guava.base.basicutilities.optional;

import com.google.common.base.Optional;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/25 10:57
 * @描述：
 * @注意事项：
 */
public class OptionalEX {

    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);

        possible.isPresent(); // returns true

        possible.get(); // returns 5
    }
}
