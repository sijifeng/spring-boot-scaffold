package com.season.guava.base.joiner.joiner;

import com.season.guava.base.model.Student;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/21 9:37
 * @描述：连接器
 * @注意事项：
 */
public class JoinerEX {

    // 低层用stringBuild连接
    public static void join() {
        System.out.println(Joiner.on("; ").skipNulls().join("Harry", null, "Ron", "Hermione"));
        System.out.println(Joiner.on(",").join(Arrays.asList(1, 5, 7)));
        Student[] students = new Student[10];
        Student s1 = new Student("张三", 1);
        Student s2 = new Student("李四", 2);
        Student s3 = new Student("王五", 2);
        Student s4 = new Student("赵六", 2);

        students[0] = s1;
        students[1] = s2;
        students[2] = s3;
        students[3] = s4;
        System.out.println(Joiner.on("-----").skipNulls().join(students));
        System.out.println(Joiner.on("-----").useForNull("空对象").join(students));

        List studentList = new ArrayList<Student>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        System.out.println(Joiner.on("-----").skipNulls().join(studentList));
    }

    public static void appendTo() {
        StringBuilder stringBuilder = new StringBuilder();
        Joiner joiner = Joiner.on('-');
        stringBuilder = joiner.appendTo(stringBuilder, "hello", "world");
        System.out.println(stringBuilder);
    }

    public static void mapJoiner() {
        Joiner.MapJoiner mapJoiner = Joiner.on("").withKeyValueSeparator("abc");
    }

    public static void main(String[] args) {
        JoinerEX.join();
        JoinerEX.appendTo();
        JoinerEX.mapJoiner();
    }
}
