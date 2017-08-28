## Package com.google.common.math
### Class IntMath
int类型的值的算术类。

---

官方API <https://google.github.io/guava/releases/18.0/api/docs/> IntMath类

final class IntMath

* * *
构造方法: 无

* * *

## 静态方法:

### 常用
static int checkedAdd(int a, int b)
> 返回a和b的总和，只要它不会溢出。

static int checkedMultiply(int a, int b)
> 返回a和b的乘积，只要它不会溢出。

static int checkedSubtract(int a, int b)
> 返回a和b的差，只要它不会溢出

static int divide(int p, int q, RoundingMode mode)
> 返回a和b的商，只要它不会溢出

static int sqrt(int x, RoundingMode mode)
> 返回x的平方根，使用指定的RoundingMode舍入模式得到相应的结果。
> > RoundingMode.UP：向远离零方向舍入取整

> > RoundingMode.DOWN：向零方向舍入取整

> > RoundingMode.FLOOR：向负无限大方向取整

> > RoundingMode.CEILING：向正无限大方向取整

> > RoundingMode.UNNECESSARY：不需要取整，如果有小数，应直接抛出ArithmeticException

> > RoundingMode.HALF_UP：如果小数部分>=0.5，则作ROUND_UP；否则，作ROUND_DOWN

> > RoundingMode.HALF_DOWN：如果小数部分<=0.5，则作ROUND_DOWN；否则，作ROUND_UP

> > RoundingMode.HALF_EVEN：如果小数部分左边的数字为奇数，则作ROUND_HALF_UP；如果它为偶数，则作ROUND_HALF_DOWN

### 不常用
static boolean isPowerOfTwo(int x)

static int log2(int x, RoundingMode mode)

static int log10(int x, RoundingMode mode)

static int pow(int b, int k)

static int mod(int x, int m)

static int gcd(int a, int b)

static int checkedPow(int b, int k)

static int factorial(int n)

static int binomial(int n, int k)

static int mean(int x, int y)