## com.google.common.base 
### Class CharMatcher

#### CharMatcher提供了多种对字符串处理的方法, 它的主要意图有:
1. 找到匹配的字符
2. 处理匹配的字符

### CharMatcher 的内部实现主要包括两部分:
1. 实现了大量公用内部类, 用来方便用户对字符串做匹配: 例如 JAVA_DIGIT 匹配数字, JAVA_LETTER 匹配字母等等。
2. 实现了大量处理字符串的方法, 使用特定的CharMatcher可以对匹配到的字符串做出多种处理, 例如 remove(), replace(), trim(), retain()等等。

---

CharMatcher本身是一个抽象类, 

其中一些操作方法是抽象方法, 

他主要依靠内部继承CharMatcher的内部子类来实现抽象方法和重写一些操作方

法, 因为不同的匹配规则的这些操作方法具有不同的实现要求。

---
1、默认实现类

CharMatcher本身提供了很多CharMatcher实现类,如下: 

* ANY: 匹配任何字符
* ASCII: 匹配是否是ASCII字符
* BREAKING_WHITESPACE: 匹配所有可换行的空白字符(不包括非换行空白字符,例如"\u00a0")
* DIGIT: 匹配ASCII数字 
* INVISIBLE: 匹配所有看不见的字符
* JAVA_ DIGIT: 匹配UNICODE数字, 使用 Character.isDigit() 实现
* JAVA_ ISO_CONTROL: 匹配ISO控制字符, 使用 Charater.isISOControl() 实现
* JAVA_ LETTER: 匹配字母, 使用 Charater.isLetter() 实现
* JAVA_ LETTER_OR_DIGET: 匹配数字或字母
* JAVA_ LOWER_CASE: 匹配小写
* JAVA_ UPPER_CASE: 匹配大写
* NONE: 不匹配所有字符
* SINGLE_WIDTH: 匹配单字宽字符, 如中文字就是双字宽
* WHITESPACE: 匹配所有空白字符

2、常用操作方法

* CharMatcher is(char match): 返回匹配指定字符的Matcher
	
		// 返回匹配指定字符的Matcher
        CharMatcher charMatcher = CharMatcher.is('a');
        System.out.println(charMatcher.matchesAnyOf("bc"));	
		// false
	

* CharMatcher isNot(char match): 返回不匹配指定字符的Matcher


* CharMatcher anyOf(CharSequence sequence): 返回匹配sequence中任意字符的Matcher


* CharMatcher noneOf(CharSequence sequence): 返回不匹配sequence中任何一个字符的Matcher


* CharMatcher inRange(char startInclusive, char endIncludesive): 返回匹配范围内任意字符的Matcher


* CharMatcher forPredicate(Predicate<? super Charater> predicate): 返回使用predicate的apply()判断匹配的Matcher


* CharMatcher negate(): 返回以当前Matcher判断规则相反的Matcher


* CharMatcher and(CharMatcher other): 返回与other匹配条件组合做与来判断的Matcher


* CharMatcher or(CharMatcher other): 返回与other匹配条件组合做或来判断的Matcher


* boolean matchesAnyOf(CharSequence sequence): 只要sequence中有任意字符能匹配Matcher,返回true

	 	CharMatcher charMatcher = CharMatcher.isNot('a');
        System.out.println(charMatcher.matchesAnyOf("abc"));
		// true

* boolean matchesAllOf(CharSequence sequence): sequence中所有字符都能匹配Matcher,返回true


* boolean matchesNoneOf(CharSequence sequence): sequence中所有字符都不能匹配Matcher,返回true


* int indexIn(CharSequence sequence): 返回sequence中匹配到的第一个字符的坐标


* int indexIn(CharSequence sequence, int start): 返回从start开始,在sequence中匹配到的第一个字符的坐标


* int lastIndexIn(CharSequence sequence): 返回sequence中最后一次匹配到的字符的坐标


* int countIn(CharSequence sequence): 返回sequence中匹配到的字符计数


* String removeFrom(CharSequence sequence): 删除sequence中匹配到到的字符并返回


* String retainFrom(CharSequence sequence): 保留sequence中匹配到的字符并返回


* String replaceFrom(CharSequence sequence, char replacement): 替换sequence中匹配到的字符并返回

* String trimFrom(CharSequence sequence): 删除首尾匹配到的字符并返回


* String trimLeadingFrom(CharSequence sequence): 删除首部匹配到的字符


* String trimTrailingFrom(CharSequence sequence): 删除尾部匹配到的字符


* String collapseFrom(CharSequence sequence, char replacement): 将匹配到的组(连续匹配的字符)替换成replacement

 
* String trimAndCollapseFrom(CharSequence sequence, char replacement): 先trim在replace

---
（1）使用预定义的常量 (predefine CharMatcher)：

* CharMatcher.WHITESPACE (Java whitespace character)
* CharMatcher.JAVA_DIGIT
* CharMatcher.JAVA_LETTER
* CharMatcher.JAVA_LOWER_CASE 
* CharMatcher.JAVA_UPPER_CASE 
* CharMatcher.ASCII
* CharMatcher.ANY


		String str = "FirstName LastName +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
		// Use a predefined constant (predefine CharMatcher) 
		CharMatcher.DIGIT.retainFrom(str);
		 
		Output:->
		"1123456789"
		 
		CharMatcher.JAVA_LETTER.retainFrom(str);
		 
		Output:->
		"FirstNameLastName"
		 
		CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(str);
		 
		Output:->
		"FirstNameLastName1123456789"
		 
		CharMatcher.ANY.countIn(str)
		 
		Output:->
		54
		 
		CharMatcher.DIGIT.countIn(str);
		 
		Output:->
		10

（2）使用工厂方法：

* CharMatcher.is('x')
* CharMatcher.isNot('_')
* CharMatcher.oneOf("aeiou").negate()
* CharMatcher.inRange('a', 'z').or(inRange('A', 'Z'))
* ...

使用条件组合：

* CharMatcher and(CharMatcher other) 
* CharMatcher or(CharMatcher other) 
* CharMatcher negate() 

		String str = "FirstName LastName +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
		 
		CharMatcher.JAVA_LOWER_CASE.negate().retainFrom(str);
		Output:->
		"FN LN +1 123 456 789 !@#$%^&*()_+|}{:\"?><"
		 
		CharMatcher.JAVA_DIGIT.or(CharMatcher.anyOf("aeiou")).retainFrom(str);
		Output:->
		"iaeaae1123456789"

（3）一些小例子：
	//原字符串
	System.out.println(string);
	
	//去掉控制字符(\t,\n,\b...)
	System.out.println(CharMatcher.JAVA_ISO_CONTROL.removeFrom(string));
	
	//获取所有的数字
	System.out.println(CharMatcher.DIGIT.retainFrom(string));
	
	//把多个空格替换为一个包括\t,并去掉首位的空格
	System.out.println(CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' '));
	
	//把所有的数字用"*"代替
	System.out.println(CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"));
	
	//获取所有的数字和小写字母
	System.out.println(CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string));
	
	//获取所有的大写字母
	System.out.println(CharMatcher.JAVA_UPPER_CASE.retainFrom(string));
	
	//获取所有单字节长度的符号
	System.out.println(CharMatcher.SINGLE_WIDTH.retainFrom(string));
			
	/*
	原字符串:
	  ROCKY  rocky  RoCkY ~!@#$%^&*()      23(*&gS   你好	234啊   GES  
	
	去掉控制字符(\t,\n,\b...):
	  ROCKY  rocky  RoCkY ~!@#$%^&*()      23(*&gS   你好234啊   GES  
	
	获取所有的数字:
	23234
	
	把多个空格替换为一个包括\t,并去掉首位的空格:
	ROCKY rocky RoCkY ~!@#$%^&*() 23(*&gS 你好 234啊 GES
	
	把所有的数字用"*"代替:
	  ROCKY  rocky  RoCkY ~!@#$%^&*()      **(*&gS   你好	***啊   GES  
	
	获取所有的数字和小写字母:
	rockyok23g234
	
	获取所有的大写字母:
	ROCKYRCYSGES
	
	获取所有单字节长度的符号:
	  ROCKY  rocky  RoCkY ~!@#$%^&*()      23(*&gS   	234   GES  
	*/
