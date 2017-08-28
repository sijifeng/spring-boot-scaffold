package com.season.guava.base.basicutilities.ordering;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/25 19:15
 * @描述：
 * @注意事项：
 */
public class OrderingEX {
    public static void orderingMethod() {
        Ordering ordering = Ordering.natural();
        Integer mini = (Integer) ordering.min(1, 2, 3, 4, 5);
        System.out.println(mini);

        List res = ordering.greatestOf(Lists.newArrayList(1, 2, 3, 4, 5, 6, 7), 3);
        System.out.println(res);
    }

    public static void main(String[] args) {
        OrderingEX.orderingMethod();
    }
}
