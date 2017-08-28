package com.season.guava.base.basicutilities.object;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author chentf(水言Dade)
 * @e-mail tfchen5211@foxmail.com
 * @date 2017/7/25 16:35
 * @描述：
 * @注意事项：
 */
public class ObjectEX implements Comparable<ObjectEX> {

    private Integer age;

    private String name;

    public static void equal() {
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(age, name);
    }


    @Override
    public String toString() {
        ToStringBuilder.reflectionToString(this);
        return Objects.toStringHelper(this).add("age", this.age).toString();
    }


    @Override
    public int compareTo(ObjectEX o) {
        return ComparisonChain.start().compare(this.age, o.age).compare(this.name, o.name).result();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ObjectEX objectEX = new ObjectEX();
        objectEX.setAge(12);
        objectEX.setName("wangshifu");
        System.out.println(objectEX);
    }
}
