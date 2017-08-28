package com.season.guava.collect;

import com.season.guava.base.model.Student;
import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/24 11:21
 * @描述：
 * @注意事项：
 */
public class CollectEx {
    public static void instacne() {
        List<Student> students = Lists.newArrayList();
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        // 期望长度为100 会自动扩充
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
        Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);

        // guava的扩展集合
        Multiset<String> multiset = HashMultiset.create();
    }

    public static void iterable() {
        Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6));
        Integer lastAdded = Iterables.getLast(concatenated);

        List<Integer> list = Lists.newArrayList(1);
        Integer theElement = Iterables.getOnlyElement(list);

        System.out.println(Iterables.frequency(Ints.asList(1, 2, 3, 4, 5, 6, 1), 1));

        List ints = Ints.asList(1, 2, 3, 4, 5, 6, 1);

        Iterable sub = Iterables.partition(ints, 3);
        sub.forEach(t -> System.out.println(t));
//        System.out.println(sub);

        sub = Iterables.unmodifiableIterable(ints);
        System.out.println(sub);

        sub = Iterables.limit(ints, 2);
        System.out.println(sub);

        sub = Iterables.limit(ints, 1);
        Iterables.getOnlyElement(sub);
    }


    public static void likeCollection() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
        // 不可变长度
        List<Integer> list2 = Ints.asList(2, 3, 4, 7, 8);
        Iterables.addAll(list1, list2);
        System.out.println(list1);
    }

    public static void listsFactoryMethod() {
        // base
        List list1 = Lists.newArrayList();
        // with elements
        List list2 = Lists.newArrayList(1, 2, 3, 4, 5);
        // with exact capacity
        List list3 = Lists.newArrayListWithExpectedSize(10);
        //  with expected size
        List list4 = Lists.newArrayListWithCapacity(10);

        Iterable iterable = Iterables.concat(Ints.asList(1, 2, 3));
        List list5 = Lists.newArrayList(iterable);

        Iterator iterator = list1.iterator();
        List list6 = Lists.newArrayList(iterator);
    }

    private static void setsMethods() {
        System.out.println("-----------------");
        Set set1 = Sets.newHashSet(1, 2, 3, 4, 5);
        Set set2 = Sets.newHashSet(22, 2, 3, 5);
        System.out.println(Sets.union(set1, set2));
        System.out.println(Sets.intersection(set1, set2));
        System.out.println(Sets.difference(set1, set2));
        System.out.println(Sets.symmetricDifference(set1, set2));
    }

    public static void mapsFactoryMethod() {
        System.out.println("---------");
        Map res = Maps.uniqueIndex(Lists.newArrayList("a", "abc", "abcd"), new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });
        System.out.println(res);

        System.out.println("-----------------");
        Map<String, Integer> left = Maps.newHashMap();
        left.put("a", 1);
        left.put("b", 2);
        left.put("d", 2);
        Map<String, Integer> right = Maps.newHashMap();
        right.put("b", 2);
        right.put("c", 4);
        right.put("d", 5);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesDiffering());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());


    }

    public static void main(String[] args) {
        CollectEx.instacne();
        CollectEx.iterable();
        CollectEx.likeCollection();
        CollectEx.listsFactoryMethod();
        CollectEx.setsMethods();
        CollectEx.mapsFactoryMethod();
    }


}
