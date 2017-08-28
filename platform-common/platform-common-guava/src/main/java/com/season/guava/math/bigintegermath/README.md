## Package com.google.common.math
### Class BigIntegerMath
BigInteger类型的值的算术类。

---

官方API <https://google.github.io/guava/releases/18.0/api/docs/> BigIntegerMath类

final class  BigIntegerMath

* * *
构造方法: 无

* * *

## 静态方法:

### 常用
static BigInteger divide(BigInteger p, BigInteger q, RoundingMode mode)
> 返回除以p由q，使用指定的RoundingMode相应的结果。
> > RoundingMode.UP：向远离零方向舍入取整

> > RoundingMode.DOWN：向零方向舍入取整

> > RoundingMode.FLOOR：向负无限大方向取整

> > RoundingMode.CEILING：向正无限大方向取整

> > RoundingMode.UNNECESSARY：不需要取整，如果有小数，应直接抛出ArithmeticException

> > RoundingMode.HALF_UP：如果小数部分>=0.5，则作ROUND_UP；否则，作ROUND_DOWN

> > RoundingMode.HALF_DOWN：如果小数部分<=0.5，则作ROUND_DOWN；否则，作ROUND_UP

> > RoundingMode.HALF_EVEN：如果小数部分左边的数字为奇数，则作ROUND_HALF_UP；如果它为偶数，则作ROUND_HALF_DOWN

static BigInteger sqrt(BigInteger x, RoundingMode mode)
> 返回x的平方根，使用指定的RoundingMode舍入模式得到相应的结果。

### 不常用
static int log2(BigInteger x, RoundingMode mode)

static BigInteger binomial(int n, int k)

static BigInteger factorial(int n)

static boolean isPowerOfTwo(BigInteger x)

static int log10(BigInteger x, RoundingMode mode)

static int log2(BigInteger x, RoundingMode mode)