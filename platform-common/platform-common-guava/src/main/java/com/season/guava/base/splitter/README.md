## com.google.common.base 
### Class Splitter

此类是用来分割字符串的

先看传统的方法

	System.out.println(Arrays.asList(",a,,b,".split(",")));

打印?

1. “”, “a”, “”, “b”, “”
2. null, “a”, null, “b”, null
3. “a”, null, “b”
4. “a”, “b”
5. 以上都不对

答案是 5：””, “a”, “”, “b” 只有尾部的空字符串被忽略了。

### 傻瓜介绍 tldr(Too Long, Didn't Read) 太长不想读

	Splitter.on(',').split("foo,bar");
	
	//打印 [foo, bar]

	Splitter.on(',').split("foo,,bar, quux");
	
	//打印 [foo, , bar,  quux]

	Splitter MY_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
    MY_SPLITTER.split("foo,,bar, quux");
	
	//打印 [foo, bar, quux]

主意splitter是不可变的，线程安全的是静态最终的实例方，以下写法是不可取的

	// Bad! Do not do this!
	   Splitter splitter = Splitter.on('/');
	   splitter.trimResults(); // does nothing!
	   return splitter.split("wrong / wrong / wrong");
	
### 蜂巢类(用法差不多的类)

static class	Splitter.MapSplitter 

### 方法概要 

## splite

	Iterable<String>	split(CharSequence sequence)

## splitter的各种实例

☆☆☆static Splitter	fixedLength(int length)
 
>返回一个根据固定长度的Splitter
>
	Splitter splitter = Splitter.fixedLength(2);
    Iterable iterable = splitter.split("123456789qwertyuiopoasdfghjkllxcvbnmm");
    System.out.println(iterable);
	//打印[12, 34, 56, 78, 9q, we, rt, yu, io, po, as, df, gh, jk, ll, xc, vb, nm, m]

Splitter	limit(int limit)
> 返回一个limit字符数之前的Splitter
>
	// 错误的写法 limit10没有起作用
    Splitter splitter = Splitter.fixedLength(2);
    splitter.limit(10);
    Iterable iterable = splitter.split("123456789qwertyuiopoasdfghjkllxcvbnmm");
    System.out.println(iterable);
	//打印[12, 34, 56, 78, 9q, we, rt, yu, io, po, as, df, gh, jk, ll, xc, vb, nm, m]
>
    //正确的写法
    splitter = Splitter.fixedLength(2).limit(10);
    iterable = splitter.split("123456789qwertyuiopoasdfghjkllxcvbnmm");
    System.out.println(iterable);
	//打印[12, 34, 56, 78, 9q, we, rt, yu, io, poasdfghjkllxcvbnmm]

☆☆☆Splitter	omitEmptyStrings()
> 忽略空字符串
>
	Splitter splitter = Splitter.on(",").omitEmptyStrings();
    Iterable iterable = splitter.split("1,,5,6,7,8,,,,uiosd,,ghjcvbnmm");
    System.out.println(iterable);
	//打印[1, 5, 6, 7, 8, uiosd, ghjcvbnmm]

static Splitter	on(CharMatcher separatorMatcher) 
>返回一个使用字符比较器拆分的Splitter


static Splitter	on(Pattern separatorPattern) 
static Splitter	onPattern(String separatorPattern) 

> 这两个方法差不多，根据正则表达式获取Splitter

static Splitter	on(String separator) 
> 最简单的使用方法


## splitter result处理

Splitter	trimResults()
> splite之后的结果进行trim
> 
	Splitter MY_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();
    MY_SPLITTER.split("foo,,bar, quux");
>
	//打印 [foo, bar, quux]

Splitter	trimResults(CharMatcher trimmer) 
> 根据字符比较器来trime

## 用于生成Splitter.MapSplitter的方法
Splitter.MapSplitter	withKeyValueSeparator(Splitter keyValueSplitter) 
> todo

Splitter.MapSplitter	withKeyValueSeparator(String separator) 
> todo