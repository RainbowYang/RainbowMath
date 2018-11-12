package moe.rainbowyang.math.operation

/**
 * 抽象三角函数运算
 * 依赖[Multiplication]
 * @author Rainbow Yang
 */
interface TrigonometricFunctions<T : Multiplication<T>> : Operation<T> {
    fun sin(): T
    fun cos(): T
    fun tan(): T = sin() / cos()
    fun cot(): T = cos() / sin()
    fun sec(): T = cos().reciprocal()
    fun csc(): T = sin().reciprocal()
}

fun <T : TrigonometricFunctions<T>> sin(num: T): T = num.sin()
fun <T : TrigonometricFunctions<T>> cos(num: T): T = num.cos()
fun <T : TrigonometricFunctions<T>> tan(num: T): T = num.tan()
fun <T : TrigonometricFunctions<T>> cot(num: T): T = num.cot()
fun <T : TrigonometricFunctions<T>> sec(num: T): T = num.sec()
fun <T : TrigonometricFunctions<T>> csc(num: T): T = num.csc()