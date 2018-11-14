package moe.rainbowyang.math.operation

/**
 * Operation包扩展
 * @author: Rainbow Yang
 * @create: 2018-11-14 17:38
 **/

fun <T : Exponentiation<T>> power(base: T, index: T) = base.power(index)
fun <T : Exponentiation<T>> log(base: T, num: T) = base.log(num)
fun <T : Exponentiation<T>> exp(index: T) = index.exp()
fun <T : Exponentiation<T>> ln(num: T) = num.ln()

fun <T : HyperbolicFunction<T>> sinh(num: T): T = num.sinh()
fun <T : HyperbolicFunction<T>> cosh(num: T): T = num.cosh()
fun <T : HyperbolicFunction<T>> tanh(num: T): T = num.tanh()
fun <T : HyperbolicFunction<T>> coth(num: T): T = num.coth()
fun <T : HyperbolicFunction<T>> sech(num: T): T = num.sech()
fun <T : HyperbolicFunction<T>> csch(num: T): T = num.csch()

fun <T : TrigonometricFunction<T>> sin(num: T): T = num.sin()
fun <T : TrigonometricFunction<T>> cos(num: T): T = num.cos()
fun <T : TrigonometricFunction<T>> tan(num: T): T = num.tan()
fun <T : TrigonometricFunction<T>> cot(num: T): T = num.cot()
fun <T : TrigonometricFunction<T>> sec(num: T): T = num.sec()
fun <T : TrigonometricFunction<T>> csc(num: T): T = num.csc()