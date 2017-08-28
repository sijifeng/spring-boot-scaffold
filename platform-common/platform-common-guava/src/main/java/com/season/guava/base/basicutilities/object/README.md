## 常见Object方法

### equals

当一个对象中的字段可以为null时，实现Object.equals方法会很痛苦，因为不得不分别对它们进行null检查。

使用Objects.equal帮助你执行null敏感的equals判断，从而避免抛出NullPointerException。例如:

	Objects.equal("a", "a"); // returns true
    Objects.equal(null, "a"); // returns false
    Objects.equal("a", null); // returns false
    Objects.equal(null, null); // returns true


### hashCode

用对象的所有字段作散列[hash]运算应当更简单。Guava的Objects.hashCode(Object...)会对传入的字段序列计算出合理的、顺序敏感的散列值。

你可以使用Objects.hashCode(field1, field2, …, fieldn)来代替手动计算散列值。

也可以使用IDE自动生成HASHCODE

	@Override
    public int hashCode() {
        return Objects.hashCode(age, name);
    }


### toString

好的toString方法在调试时是无价之宝，但是编写toString方法有时候却很痛苦。使用 Objects.toStringHelper可以轻松编写有用的toString方法。

例如：

	// Returns "ClassName{x=1}"
	Objects.toStringHelper(this).add("x", 1).toString();
	
	// Returns "MyObject{x=1}"
	Objects.toStringHelper("MyObject").add("x", 1).toString();

我觉得以上toString方法写起来麻烦的很，推荐lang包里面的ToStringBuilder

org.apache.commons.lang3.builder

	ToStringBuilder.reflectionToString(this);

### compare/compareTo

我们应该能把这种代码变得更优雅，为此，Guava提供了ComparisonChain。

ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。

 	@Override
    public int compareTo(ObjectEX o) {
        return ComparisonChain.start().compare(this.age, o.age).compare(this.name, o.name).result();
    }

这种Fluent接口风格的可读性更高，发生错误编码的几率更小，并且能避免做不必要的工作。更多Guava排序器工具可以在下一节里找到。