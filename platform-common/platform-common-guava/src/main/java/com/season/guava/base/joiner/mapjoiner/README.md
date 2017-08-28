## com.google.common.base 
### Class Joiner.MapJoiner

此类是用来把map对象拼成字符串的方法。

## 构造方法 

无

## 如何获得此对象

☆☆☆通过Joiner类获得

>
	Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");

## 主要方法

### append

	<A extends Appendable> A appendTo(A appendable, Iterable<? extends Map.Entry<?,?>> entries)
	
	<A extends Appendable> A appendTo(A appendable, Map<?,?> map)  
	
	StringBuilder	appendTo(StringBuilder builder, Iterable<? extends Map.Entry<?,?>> entries) 

	StringBuilder	appendTo(StringBuilder builder, Map<?,?> map) 

### 样例

	Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
    Map<String, String> map = Maps.newLinkedHashMap();
    map.put("name", "doctor");
    map.put("sex", "man");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder = mapJoiner.appendTo(stringBuilder, map);
	
	//输出name=doctor&sex=man

### ☆☆☆join方法

	String	join(Iterable<? extends Map.Entry<?,?>> entries) 
	
	☆☆☆String	join(Map<?,?> map) 

### 样例 
	
	Joiner.MapJoiner mapJoiner = Joiner.on("&").withKeyValueSeparator("=");
    Map<String, String> map = Maps.newLinkedHashMap();
    map.put("name", "doctor");
    map.put("sex", "man");
    System.out.println(mapJoiner.join(map));
	//输出name=doctor&sex=man	

### null处理

	Joiner.MapJoiner	useForNull(String nullText) 