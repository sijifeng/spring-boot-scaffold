## Guava强大的”流畅风格比较器”

### Ordering

从实现上说，Ordering实例就是一个特殊的Comparator实例。Ordering把很多基于Comparator的静态方法（如Collections.max）包装为自己的

实例方法（非静态方法），并且提供了链式调用方法，来定制和增强现有的比较器。

创建排序器：常见的排序器可以由下面的静态方法创建

#### natural()	

对可排序类型做自然排序，如数字按大小，日期按先后排序

#### usingToString()	

按对象的字符串形式做字典排序[lexicographical ordering]

#### from(Comparator)	

把给定的Comparator转化为排序器


### 链式调用方法：通过链式调用，可以由给定的排序器衍生出其它排序器

#### reverse()	

获取语义相反的排序器
#### nullsFirst()	

使用当前排序器，但额外把null值排到最前面。
#### nullsLast()	

使用当前排序器，但额外把null值排到最后面。
#### compound(Comparator)	

合成另一个比较器，以处理当前排序器中的相等情况。
#### lexicographical()	

基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
#### onResultOf(Function)	

对集合中元素调用Function，再按返回值用当前排序器排序。

### 运用排序器：Guava的排序器实现有若干操纵集合或元素值的方法

#### greatestOf(Iterable iterable, int k)	

获取可迭代对象中最大的k个元素。
#### isOrdered(Iterable)	

判断可迭代对象是否已按排序器排序：允许有排序值相等的元素。
#### sortedCopy(Iterable)	

判断可迭代对象是否已严格按排序器排序：不允许排序值相等的元素。	immutableSortedCopy
#### min(E, E)	

返回两个参数中最小的那个。如果相等，则返回第一个参数。

#### min(E, E, E, E...)	
返回多个参数中最小的那个。如果有超过一个参数都最小，则返回第一个最小的参数。	

#### min(Iterable)	

返回迭代器中最小的元素。如果可迭代对象中没有元素，则抛出NoSuchElementException。