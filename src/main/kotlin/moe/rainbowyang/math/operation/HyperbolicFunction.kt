package moe.rainbowyang.math.operation

/**
 * 抽象双曲三角函数运算
 * 依赖[Multiplication]
 * @author Rainbow Yang
 */
interface HyperbolicFunction<T : Multiplication<T>> : Operation<T> {
    fun sinh(): T
    fun cosh(): T
    fun tanh(): T = sinh() / cosh()
    fun coth(): T = cosh() / sinh()
    fun sech(): T = cosh().reciprocal()
    fun csch(): T = sinh().reciprocal()
}

fun <T : HyperbolicFunction<T>> sinh(num: T): T = num.sinh()
fun <T : HyperbolicFunction<T>> cosh(num: T): T = num.cosh()
fun <T : HyperbolicFunction<T>> tanh(num: T): T = num.tanh()
fun <T : HyperbolicFunction<T>> coth(num: T): T = num.coth()
fun <T : HyperbolicFunction<T>> sech(num: T): T = num.sech()
fun <T : HyperbolicFunction<T>> csch(num: T): T = num.csch()