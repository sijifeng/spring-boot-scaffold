## Package com.google.common.math
### Class DoubleMath
Double类型的值的算术类。

---

官方API <https://google.github.io/guava/releases/18.0/api/docs/> DoubleMath类

final class DoubleMath

* * *
构造方法: 无

* * *

## 静态方法:

### 常用
static boolean isMathematicalInteger(double x)
> 判断该浮点数是不是一个整数

static int roundToInt(double x, RoundingMode mode)
> 取整后为int类型；对无限小数、溢出抛出异常，返回除以p由q，使用指定的RoundingMode相应的结果

> > RoundingMode.UP：向远离零方向舍入取整

> > RoundingMode.DOWN：向零方向舍入取整

> > RoundingMode.FLOOR：向负无限大方向取整

> > RoundingMode.CEILING：向正无限大方向取整

> > RoundingMode.UNNECESSARY：不需要取整，如果有小数，应直接抛出ArithmeticException

> > RoundingMode.HALF_UP：如果小数部分>=0.5，则作ROUND_UP；否则，作ROUND_DOWN

> > RoundingMode.HALF_DOWN：如果小数部分<=0.5，则作ROUND_DOWN；否则，作ROUND_UP

> > RoundingMode.HALF_EVEN：如果小数部分左边的数字为奇数，则作ROUND_HALF_UP；如果它为偶数，则作ROUND_HALF_DOWN

static long roundToLong(double x, RoundingMode mode)
> 取整后为long类型；对无限小数、溢出抛出异常

static BigInteger roundToBigInteger(double x, RoundingMode mode)
> 取整后为BigInteger类型；对无限小数抛出异常

### 不常用
static boolean isPowerOfTwo(double x)

static double log2(double x)

static double factorial(int n)

static boolean fuzzyEquals(double a, double b, double tolerance)

static int fuzzyCompare(double a, double b, double tolerance)

static double mean(double... values)

static double mean(int... values)

static double mean(long... values)

static double mean(Iterable<? extends Number> values)

static double mean(Iterator<? extends Number> values)