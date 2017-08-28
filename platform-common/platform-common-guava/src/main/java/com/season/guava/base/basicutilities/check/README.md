## 前置条件判断

Guava在Preconditions类中提供了若干前置条件判断的实用方法，我们强烈建议在Eclipse中静态导入这些方法。每个方法都有三个变种：

* 没有额外参数：抛出的异常中没有错误消息；
* 有一个Object对象作为额外参数：抛出的异常使用Object.toString() 作为错误消息；
* 有一个String对象作为额外参数，并且有一组任意数量的附加Object对象：这个变种处理异常消息的方式有点类似printf，但考虑GWT的兼容性和效率，只支持%s指示符。例如：


		checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
		
		checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
### checkArgument(boolean)	

检查boolean是否为true，用来检查传递给方法的参数。	

抛IllegalArgumentException异常。

	Preconditions.checkArgument(1 == 2);

### checkNotNull(T)

检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。

NullPointerException

	String str = null;
    Preconditions.checkNotNull(str);

### checkState(boolean)

用来检查对象的某些状态。

IllegalStateException
	
	Preconditions.checkState(1 == 2);

### checkElementIndex(int index, int size)

检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size 

IndexOutOfBoundsException

	Preconditions.checkElementIndex(2, 2);

### checkPositionIndex(int index, int size)

检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size

IndexOutOfBoundsException
	
	Preconditions.checkPositionIndex(2, 2);

### checkPositionIndexes(int start, int end, int size)

检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效

IndexOutOfBoundsException

	Preconditions.checkPositionIndexes(0, 5, 5);

### 注意index和position是不同的


