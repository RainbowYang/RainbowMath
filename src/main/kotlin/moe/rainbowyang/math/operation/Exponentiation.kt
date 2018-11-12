package moe.rainbowyang.math.operation

/**
 * 抽象幂运算
 * 依赖[Multiplication]
 * @author: Rainbow Yang
 * @create: 2018-11-12 12:03
 **/
interface Exponentiation<T> : Operation<T>
        where T : Exponentiation<T>, T : Multiplication<T> {
    infix fun power(other: T): T = (this.ln() * other).exp()
    infix fun log(other: T): T = other.ln() / this.ln()

    fun exp(): T
    fun ln(): T
}

fun <T : Exponentiation<T>> power(base: T, index: T) = base.power(index)
fun <T : Exponentiation<T>> log(base: T, num: T) = base.log(num)
fun <T : Exponentiation<T>> exp(index: T) = index.exp()
fun <T : Exponentiation<T>> ln(num: T) = num.ln()