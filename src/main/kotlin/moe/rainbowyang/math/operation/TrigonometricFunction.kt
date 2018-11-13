package moe.rainbowyang.math.operation

/**
 * 抽象三角函数运算
 * 依赖[Multiplication]
 * @author Rainbow Yang
 */
interface TrigonometricFunction<T : Multiplication<T>> : Operation<T> {
    fun sin(): T
    fun cos(): T
    fun tan(): T = sin() / cos()
    fun cot(): T = cos() / sin()
    fun sec(): T = cos().reciprocal()
    fun csc(): T = sin().reciprocal()
}

fun <T : TrigonometricFunction<T>> sin(num: T): T = num.sin()
fun <T : TrigonometricFunction<T>> cos(num: T): T = num.cos()
fun <T : TrigonometricFunction<T>> tan(num: T): T = num.tan()
fun <T : TrigonometricFunction<T>> cot(num: T): T = num.cot()
fun <T : TrigonometricFunction<T>> sec(num: T): T = num.sec()
fun <T : TrigonometricFunction<T>> csc(num: T): T = num.csc()