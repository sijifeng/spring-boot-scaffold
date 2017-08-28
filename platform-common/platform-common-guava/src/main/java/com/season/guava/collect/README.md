## 不可变集合

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="184"><b>可变集合接口</b><b></b></td>
<td valign="top" width="158"><b>属于</b><b>JDK</b><b>还是</b><b>Guava</b><b></b></td>
<td width="277"><b>不可变版本</b><b></b></td>
</tr>
<tr>
<td width="184">Collection</td>
<td width="158">JDK</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableCollection.html"><tt>ImmutableCollection</tt></a></td>
</tr>
<tr>
<td width="184">List</td>
<td width="158">JDK</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableList.html"><tt>ImmutableList</tt></a></td>
</tr>
<tr>
<td width="184">Set</td>
<td width="158">JDK</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableSet.html"><tt>ImmutableSet</tt></a></td>
</tr>
<tr>
<td width="184">SortedSet/NavigableSet</td>
<td width="158">JDK</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableSortedSet.html"><tt>ImmutableSortedSet</tt></a></td>
</tr>
<tr>
<td width="184">Map</td>
<td width="158">JDK</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableMap.html"><tt>ImmutableMap</tt></a></td>
</tr>
<tr>
<td width="184">SortedMap</td>
<td width="158">JDK</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableSortedMap.html"><tt>ImmutableSortedMap</tt></a></td>
</tr>
<tr>
<td width="184"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Multiset">Multiset</a></td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableMultiset.html"><tt>ImmutableMultiset</tt></a></td>
</tr>
<tr>
<td width="184">SortedMultiset</td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/ImmutableSortedMultiset.html"><tt>ImmutableSortedMultiset</tt></a></td>
</tr>
<tr>
<td width="184"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Multimap">Multimap</a></td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableMultimap.html"><tt>ImmutableMultimap</tt></a></td>
</tr>
<tr>
<td width="184">ListMultimap</td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableListMultimap.html"><tt>ImmutableListMultimap</tt></a></td>
</tr>
<tr>
<td width="184">SetMultimap</td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableSetMultimap.html"><tt>ImmutableSetMultimap</tt></a></td>
</tr>
<tr>
<td width="184"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#BiMap">BiMap</a></td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableBiMap.html"><tt>ImmutableBiMap</tt></a></td>
</tr>
<tr>
<td width="184"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#ClassToInstanceMap">ClassToInstanceMap</a></td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableClassToInstanceMap.html"><tt>ImmutableClassToInstanceMap</tt></a></td>
</tr>
<tr>
<td width="184"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Table">Table</a></td>
<td width="158">Guava</td>
<td width="277"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableTable.html"><tt>ImmutableTable</tt></a></td>
</tr>
</tbody>
</table>


## guava新的集合类型

这块不做详细介绍，有兴趣的同学可以自己详细查看

### Multiset

可以用两种方式看待Multiset：

* 没有元素顺序限制的ArrayList<E>

* Map<E, Integer>，键为元素，值为计数

### Multimap

每个有经验的Java程序员都在某处实现过Map<K, List<V>>或Map<K, Set<V>>，并且要忍受这个结构的笨拙。

例如，Map<K, Set<V>>通常用来表示非标定有向图。Guava的 Multimap可以很容易地把一个键映射到多个值。

换句话说，Multimap是把键映射到任意多个值的一般方式。

### BiMap

传统上，实现键值对的双向映射需要维护两个单独的map，并保持它们间的同步。但这种方式很容易出错，而且对于值已经在map中的情况，会变得非常

混乱。

BiMap<K, V>是特殊的Map：

* 可以用 inverse()反转BiMap<K, V>的键值映射

* 保证值是唯一的，因此 values()返回Set而不是普通的Collection

### Table

通常来说，当你想使用多个键做索引的时候，你可能会用类似Map<FirstName, Map<LastName, Person>>的实现，这种方式很丑陋，使用上也不

友好。Guava为此提供了新集合类型Table，它有两个支持所有类型的键：”行”和”列”。Table提供多种视图，以便你从各种角度使用它：

* rowMap()：用Map<R, Map<C, V>>表现Table<R, C, V>。同样的， rowKeySet()返回”行”的集合Set<R>。

* row(r) ：用Map<C, V>返回给定”行”的所有列，对这个map进行的写操作也将写入Table中。

* 类似的列访问方法：columnMap()、columnKeySet()、column(c)。（基于列的访问会比基于的行访问稍微低效点）

* cellSet()：用元素类型为Table.Cell<R, C, V>的Set表现Table<R, C, V>。Cell类似于Map.Entry，但它是用行和列两个键区分的。

### ClassToInstanceMap

ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。

### RangeSet

RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。

### RangeMap

RangeMap描述了”不相交的、非空的区间”到特定值的映射。和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。

## 强大的集合工具类：java.util.Collections中未包含的集合工具

### 集合工具

任何对JDK集合框架有经验的程序员都熟悉和喜欢java.util.Collections包含的工具方法。

Guava沿着这些路线提供了更多的工具方法：适用于所有集合的静态方法。这是Guava最流行和成熟的部分之一。

我们用相对直观的方式把工具类与特定集合接口的对应关系归纳如下：

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="96"><b>集合接口</b><b></b></td>
<td width="144"><b>属于</b><b>JDK</b><b>还是</b><b>Guava</b></td>
<td width="372"><b>对应的</b><b>Guava</b><b>工具类</b><b></b></td>
</tr>
<tr>
<td width="96">Collection</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Collections2.html"><tt>Collections2</tt></a>：不要和java.util.Collections混淆</td>
</tr>
<tr>
<td width="96">List</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Lists.html"><tt>Lists</tt></a></td>
</tr>
<tr>
<td width="96">Set</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Sets.html"><tt>Sets</tt></a></td>
</tr>
<tr>
<td width="96">SortedSet</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Sets.html"><tt>Sets</tt></a></td>
</tr>
<tr>
<td width="96">Map</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Maps.html"><tt>Maps</tt></a></td>
</tr>
<tr>
<td width="96">SortedMap</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Maps.html"><tt>Maps</tt></a></td>
</tr>
<tr>
<td width="96">Queue</td>
<td width="144">JDK</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/collect/Queues.html"><tt>Queues</tt></a></td>
</tr>
<tr>
<td width="96"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Multiset">Multiset</a></td>
<td width="144">Guava</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Multisets.html"><tt>Multisets</tt></a></td>
</tr>
<tr>
<td width="96"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Multimap">Multimap</a></td>
<td width="144">Guava</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Multimaps.html"><tt>Multimaps</tt></a></td>
</tr>
<tr>
<td width="96"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#BiMap">BiMap</a></td>
<td width="144">Guava</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Maps.html"><tt>Maps</tt></a></td>
</tr>
<tr>
<td width="96"><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Table">Table</a></td>
<td width="144">Guava</td>
<td width="372"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Tables.html"><tt>Tables</tt></a></td>
</tr>
</tbody>
</table>


最下面4个是Guava的新型集合，有兴趣可以自己研究。


### 使用静态工厂方法
如下：

	List<Student> students = Lists.newArrayList();
    List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

    List<String> exactly100 = Lists.newArrayListWithCapacity(100);
    // 期望长度为100 会自动扩充
    List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
    Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);



注意：Guava引入的新集合类型没有暴露原始构造器，也没有在工具类中提供初始化方法。而是直接在集合类中提供了静态工厂方法，例如：
	Multiset<String> multiset = HashMultiset.create();


## Iterables

在可能的情况下，Guava提供的工具方法更偏向于接受Iterable而不是Collection类型。

在Google，对于不存放在主存的集合——比如从数据库或其他数据中心收集的结果集，因为实际上还没有攫取全部数据，这类结果集都不能支持类似size()的操作 ——通常都不会用Collection类型来表示。

因此，很多你期望的支持所有集合的操作都在Iterables类中。

大多数Iterables方法有一个在Iterators类中的对应版本，用来处理Iterator。

截至Guava 1.2版本，Iterables使用FluentIterable类进行了补充，它包装了一个Iterable实例，并对许多操作提供了”fluent”（链式调用）语法。

### 常规方法
<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#concat(java.lang.Iterable)"><tt>concat(Iterable&lt;Iterable&gt;)</tt></a></td>
<td width="222">串联多个iterables的懒视图*</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#concat(java.lang.Iterable...)"><tt>concat(Iterable...)</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#frequency(java.lang.Iterable, java.lang.Object)"><tt>frequency(Iterable, Object)</tt></a></td>
<td width="222">返回对象在iterable中出现的次数</td>
<td width="211">与Collections.frequency (Collection, &nbsp; Object)比较；<tt></tt><a href="http://code.google.com/p/guava-libraries/wiki/NewCollectionTypesExplained#Multiset">Multiset</a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#partition(java.lang.Iterable, int)"><tt>partition(Iterable, int)</tt></a></td>
<td width="222">把iterable按指定大小分割，得到的子集都不能进行修改操作</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#partition(java.util.List, int)"><tt>Lists.partition(List, int)</tt></a>；<a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#paddedPartition(java.lang.Iterable, int)"><tt>paddedPartition(Iterable, int)</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#getFirst(java.lang.Iterable, T)"><tt>getFirst(Iterable, T default)</tt></a></td>
<td width="222">返回iterable的第一个元素，若iterable为空则返回默认值</td>
<td width="211">与Iterable.iterator(). next()比较;<a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#first()"><tt>FluentIterable.first()</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#getLast(java.lang.Iterable)"><tt>getLast(Iterable)</tt></a></td>
<td width="222">返回iterable的最后一个元素，若iterable为空则抛出NoSuchElementException</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#getLast(java.lang.Iterable, T)"><tt>getLast(Iterable, T default)</tt></a>；<br>
<a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#last()"><tt>FluentIterable.last()</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#elementsEqual(java.lang.Iterable, java.lang.Iterable)"><tt>elementsEqual(Iterable, Iterable)</tt></a></td>
<td width="222">如果两个iterable中的所有元素相等且顺序一致，返回true</td>
<td width="211">与List.equals(Object)比较</td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#unmodifiableIterable(java.lang.Iterable)"><tt>unmodifiableIterable(Iterable)</tt></a></td>
<td width="222">返回iterable的不可变视图</td>
<td width="211">与Collections. unmodifiableCollection(Collection)比较</td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#limit(java.lang.Iterable, int)"><tt>limit(Iterable, int)</tt></a></td>
<td width="222">限制iterable的元素个数限制给定值</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#limit(int)"><tt>FluentIterable.limit(int)</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#getOnlyElement(java.lang.Iterable)"><tt>getOnlyElement(Iterable)</tt></a></td>
<td width="222">获取iterable中唯一的元素，如果iterable为空或有多个元素，则快速失败</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#getOnlyElement(java.lang.Iterable, T)"><tt>getOnlyElement(Iterable, T default)</tt></a></td>
</tr>
</tbody>
</table>


代码示例
	Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3), Ints.asList(4, 5, 6));
    Integer lastAdded = Iterables.getLast(concatenated);

    List<Integer> list = Lists.newArrayList(1);
    Integer theElement = Iterables.getOnlyElement(list);

    System.out.println(Iterables.frequency(Ints.asList(1, 2, 3, 4, 5, 6, 1), 1));

    List ints = Ints.asList(1, 2, 3, 4, 5, 6, 1);

    Iterable sub = Iterables.partition(ints, 3);
    sub.forEach(t -> System.out.println(t));

    sub = Iterables.unmodifiableIterable(ints);
    System.out.println(sub);

    sub = Iterables.limit(ints, 2);
    System.out.println(sub);

    sub = Iterables.limit(ints, 1);
    Iterables.getOnlyElement(sub);

### 与Collection方法相似的工具方法

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="186"><b>方法</b><b></b></td>
<td width="222"><b>类似的</b><b>Collection</b><b>方法</b><b></b></td>
<td width="211"><b>等价的</b><b>FluentIterable</b><b>方法</b><b></b></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#addAll(java.util.Collection, java.lang.Iterable)"><tt>addAll(Collection addTo, &nbsp; Iterable toAdd)</tt></a></td>
<td width="222">Collection.addAll(Collection)</td>
<td width="211"></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#contains(java.lang.Iterable, java.lang.Object)"><tt>contains(Iterable, Object)</tt></a></td>
<td width="222">Collection.contains(Object)</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#contains(java.lang.Object)"><tt>FluentIterable.contains(Object)</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#removeAll(java.lang.Iterable, java.util.Collection)"><tt>removeAll(Iterable &nbsp; removeFrom, Collection toRemove)</tt></a></td>
<td width="222">Collection.removeAll(Collection)</td>
<td width="211"></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#retainAll(java.lang.Iterable, java.util.Collection)"><tt>retainAll(Iterable &nbsp; removeFrom, Collection toRetain)</tt></a></td>
<td width="222">Collection.retainAll(Collection)</td>
<td width="211"></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#size(java.lang.Iterable)"><tt>size(Iterable)</tt></a></td>
<td width="222">Collection.size()</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#size()"><tt>FluentIterable.size()</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#toArray(java.lang.Iterable, java.lang.Class)"><tt>toArray(Iterable, Class)</tt></a></td>
<td width="222">Collection.toArray(T[])</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#toArray(java.lang.Class)"><tt>FluentIterable.toArray(Class)</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#isEmpty(java.lang.Iterable)"><tt>isEmpty(Iterable)</tt></a></td>
<td width="222">Collection.isEmpty()</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#isEmpty()"><tt>FluentIterable.isEmpty()</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#get(java.lang.Iterable, int)"><tt>get(Iterable, int)</tt></a></td>
<td width="222">List.get(int)</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-%20history/release12/javadoc/com/google/common/collect/FluentIterable.html#get(int)"><tt>FluentIterable.get(int)</tt></a></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Iterables.html#toString(java.lang.Iterable)"><tt>toString(Iterable)</tt></a></td>
<td width="222">Collection.toString()</td>
<td width="211"><a href="http://docs.guava-libraries.googlecode.com/git-history/release12/javadoc/com/google/common/collect/FluentIterable.html#toString()"><tt>FluentIterable.toString()</tt></a></td>
</tr>
</tbody>
</table>


不一一示例

	List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
	// 不可变长度
	List<Integer> list2 = Ints.asList(2, 3, 4, 7, 8);
	Iterables.addAll(list1, list2);
	System.out.println(list1);

---

## Lists

除了静态工厂方法和函数式编程方法，Lists为List类型的对象提供了若干工具方法。

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="186"><b>方法</b><b></b></td>
<td width="426"><b>描述</b><b></b></td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#partition(java.util.List, int)"><tt>partition(List, int)</tt></a></td>
<td width="426">把List按指定大小分割</td>
</tr>
<tr>
<td width="186"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#reverse(java.util.List)"><tt>reverse(List)</tt></a></td>
<td width="426">返回给定List的反转视图。注: 如果List是不可变的，考虑改用<a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/ImmutableList.html#reverse()"><tt>ImmutableList.reverse()</tt></a>。</td>
</tr>
</tbody>
</table>

####　示例

	List countUp = Ints.asList(1, 2, 3, 4, 5);
	List countDown = Lists.reverse(theList); // {5, 4, 3, 2, 1}
	List<List> parts = Lists.partition(countUp, 2);//{{1,2}, {3,4}, {5}}

### 静态工厂方法

<table border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td width="108"><b>具体实现类型</b><b></b></td>
<td width="504"><b>工厂方法</b><b></b></td>
</tr>
<tr>
<td width="108">ArrayList</td>
<td width="504"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newArrayList()">basic</a>, <a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newArrayList(E...)">with elements</a>, <a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newArrayList(java.lang.Iterable)">from <tt>Iterable</tt></a>, <a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newArrayListWithCapacity(int)">with exact capacity</a>, <a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newArrayListWithExpectedSize(int)">with expected size</a>, <a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newArrayList(java.util.Iterator)">from <tt>Iterator</tt></a></td>
</tr>
<tr>
<td width="108">LinkedList</td>
<td width="504"><a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newLinkedList()">basic</a>, <a href="http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/com/google/common/collect/Lists.html#newLinkedList(java.lang.Iterable)">from <tt>Iterable</tt></a></td>
</tr>
</tbody>
</table>

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

## Sets

Sets方法大多返回SetView

### SetView

* 直接当作Set使用，因为SetView也实现了Set接口；

* 用copyInto(Set)拷贝进另一个可变集合；

* 用immutableCopy()对自己做不可变拷贝。

方法

#### union(Set, Set)
> 合集

#### intersection(Set, Set)
> 交集

#### difference(Set, Set)
> 前面set中,除去后面set中的元素

#### symmetricDifference(Set,Set)
> 合集除去交集

	System.out.println("-----------------");
	Set set1 = Sets.newHashSet(1, 2, 3, 4, 5);
	Set set2 = Sets.newHashSet(22, 2, 3, 5);
	System.out.println(Sets.union(set1, set2));
	System.out.println(Sets.intersection(set1, set2));
	System.out.println(Sets.difference(set1, set2));
	System.out.println(Sets.symmetricDifference(set1, set2));

	[1, 2, 3, 4, 5, 22]
	[2, 3, 5]
	[1, 4]
	[1, 4, 22]

### 其他Set工具方法

#### cartesianProduct(List<Set>)	返回所有集合的笛卡儿积

#### powerSet(Set)	返回给定集合的所有子集

	Set<String> animals = ImmutableSet.of("gerbil", "hamster");
	
	Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
	
	Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
	// {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
	//  {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}
	
	Set<Set<String>> animalSets = Sets.powerSet(animals);
	// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}

 ### Sets静态工厂方法 不一示例

#### HashSet  

basic, with elements, from Iterable, with expected size, from Iterator

#### LinkedHashSet	

basic, from Iterable, with expected size

#### TreeSet	

basic, with Comparator, from Iterable


## Maps

Maps类有若干值得单独说明的、很酷的方法。

### uniqueIndex

#### 需要有一个属性 unique才能用，如果不是独一无二的请用 Multimaps.index

Maps.uniqueIndex(Iterable,Function)通常针对的场景是：有一组对象，

它们在某个属性上分别有独一无二的值，而我们希望能够按照这个属性值查找对象——译者注：

这个方法返回一个Map，键为Function返回的属性值，

值为Iterable中相应的元素，因此我们可以反复用这个Map进行查找操作。

比方说，我们有一堆字符串，这些字符串的长度都是独一无二的，

而我们希望能够按照特定长度查找字符串：

如果索引值不是独一无二的，请参见下面的Multimaps.index方法。
	
	Map res = Maps.uniqueIndex(Lists.newArrayList("a", "abc", "abcd"), new Function<String, Integer>() {
	    @Override
	    public Integer apply(String s) {
	        return s.length();
	    }
	});
	
	System.out.println(res);
	{1=a, 3=abc, 4=abcd}

### difference

Maps.difference(Map, Map)用来比较两个Map以获取所有不同点。该方法返回MapDifference对象，把不同点的维恩图分解为：

#### entriesInCommon()	两个Map中都有的映射项，包括匹配的键与值

#### entriesDiffering()	键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值

#### entriesOnlyOnLeft()	键只存在于左边Map的映射项

#### entriesOnlyOnLeft()	键只存在于左边Map的映射项

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

	{b=2}
	{d=(2, 5)}
	{a=1}
	{c=4}

### 处理BiMap 略

### 静态工厂方法

#### HashMap

basic, from Map, with expected size

#### LinkedHashMap

basic, from Map

#### TreeMap

basic, from Comparator, from SortedMap

#### EnumMap

from Class, from Map

#### ConcurrentMap：支持所有操作

basic

#### IdentityHashMap

basic

---
## Multisets


不可变Set工具类

不做介绍，有兴趣自己看。

---
## Multimaps

不可变Maps工具类

不做介绍，有兴趣自己研究。

## Tables

略