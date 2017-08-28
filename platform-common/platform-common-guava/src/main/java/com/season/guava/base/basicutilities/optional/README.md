## 使用和避免null

### 使用Optional来避免null

#### 其实这块我们用的挺少的


Optional 在JDK8中已经被官方实现在util包中

大多数情况下，开发人员使用null表明的是某种缺失情形：可能是已经有一个默认值，或没有值，或找不到值。

例如，Map.get返回null就表示找不到给定键对应的值。

Guava用Optional<T>表示可能为null的T类型引用。一个Optional实例可能包含非null的引用（我们称之为引用存在），也可能什么也不包括（称之为引用缺失）。

☆☆☆它从不说包含的是null值，而是用存在或缺失来表示。

但Optional从不会包含null值引用。

	Optional<Integer> possible = Optional.of(5);
	
	possible.isPresent(); // returns true
	
	possible.get(); // returns 5

### 创建Optional实例（以下都是静态方法）：

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#of(T)"><tt>Optional.of(T)</tt> </a></td>
<td width="419">创建指定引用的Optional实例，若引用为null则快速失败</td>
</tr>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#absent()"><tt>Optional.absent()</tt></a></td>
<td width="419">创建引用缺失的Optional实例</td>
</tr>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#fromNullable(T)"><tt>Optional.fromNullable(T)</tt></a></td>
<td width="419">创建指定引用的Optional实例，若引用为null则表示缺失</td>
</tr>
</tbody>
</table>


### 用Optional实例查询引用（以下都是非静态方法）：

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#isPresent()"><tt>boolean isPresent()</tt></a></td>
<td width="419">如果Optional包含非null的引用（引用存在），返回true</td>
</tr>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#get()"><tt>T get()</tt></a></td>
<td width="419">返回Optional所包含的引用，若引用缺失，则抛出java.lang.IllegalStateException</td>
</tr>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#or(T)"><tt>T or(T)</tt></a></td>
<td width="419">返回Optional所包含的引用，若引用缺失，返回指定的值</td>
</tr>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#orNull()"><tt>T orNull()</tt></a></td>
<td width="419">返回Optional所包含的引用，若引用缺失，返回null</td>
</tr>
<tr>
<td width="199"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/base/Optional.html#asSet()"><tt>Set&lt;T&gt; asSet()</tt></a></td>
<td width="419">返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元素的集合，如果引用缺失，返回一个空集合。</td>
</tr>
</tbody>
</table>


### 使用Optional的意义在哪儿

使用Optional除了赋予null语义，增加了可读性，最大的优点在于它是一种傻瓜式的防护。

Optional迫使你积极思考引用缺失的情况，因为你必须显式地从Optional获取引用。

直接使用null很容易让人忘掉某些情形，尽管FindBugs可以帮助查找null相关的问题，但是我们还是认为它并不能准确地定位问题根源。

如同输入参数，方法的返回值也可能是null。

和其他人一样，你绝对很可能会忘记别人写的方法method(a,b)会返回一个null，就好像当你实现method(a,b)时，也很可能忘记输入参数a可以为null。

将方法的返回类型指定为Optional，也可以迫使调用者思考返回的引用缺失的情形。



