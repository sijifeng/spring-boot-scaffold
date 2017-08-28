## com.google.common.base
### Class Joiner
此类主要处理字符串连接，以及实现Appendable接口的连接。☆☆☆方法是我认为最常用的方法。有一些方法这里没有列出，需要的同学可以自己查看官方API文档。

***

官方API <http://tool.oschina.net/apidocs/apidoc?api=guava> Joiner类

和此类差不多相同的类的概要

static class  Joiner.MapJoiner 

* * *
构造方法: 无

* * *

## 静态方法:

static Joiner on(char separator)

>该方法返回一个以separator做分割的Joiner对象

>		Joiner joiner = Joiner.on('-');


☆☆☆ static Joiner on(String separator)
>该方法返回一个以separator做分割的Joiner对象

>		Joiner joiner = Joiner.on("");

## 方法概要:

### null处理

遇到null处理的一些方法，默认抛空指针异常

☆☆☆ Joiner skipNulls()

>  遇到null跳过
>	
	Joiner joiner = Joiner.on("").skipNulls();

☆☆☆ Joiner	useForNull(String nullText) 

> 自动用 nullText变量，代替null
>
	Joiner joiner = Joiner.on("").useForNull("empty");

### 最常用的 Join方法

String join(Iterable<?> parts)
>该方法拼接实现Iterable接口的类
>
	Student s1 = new Student("张三", 1);
    Student s2 = new Student("李四", 2);
    Student s3 = new Student("王五", 2);
    Student s4 = new Student("赵六", 2);
	List studentList = new ArrayList<Student>();
    studentList.add(s1);
    studentList.add(s2);
    studentList.add(s3);
    studentList.add(s4);
    System.out.println(Joiner.on("-----").skipNulls().join(studentList));

String join(Object[] parts) 
>该方法拼接数组
> 		
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

☆☆☆ String join(Object first, Object second, Object... rest) 
>该方法拼接多个对象

>		
>		Joiner.on("; ").skipNulls().join("Harry", null, "Ron", "Hermione")

### 次常用的 append 方法
这里和上面差不多 就不一一介绍

	<A extends Appendable> A appendTo(A appendable, Iterable<?> parts)

	<A extends Appendable> A appendTo(A appendable, Object[] parts)

	<A extends Appendable> A appendTo(A appendable, Object first, Object second, Object... rest)

	StringBuilder	appendTo(StringBuilder builder, Iterable<?> parts) 

	StringBuilder	appendTo(StringBuilder builder, Object[] parts) 

	☆☆☆ StringBuilder	appendTo(StringBuilder builder, Object first, Object second, Object... rest) 
- - -
	StringBuilder stringBuilder = new StringBuilder();
    Joiner joiner = Joiner.on('-');
    stringBuilder = joiner.appendTo(stringBuilder, "hello", "world");

### 生成一个Joiner.MapJoiner对象
	Joiner.MapJoiner mapJoiner = Joiner.on("").withKeyValueSeparator("abc");