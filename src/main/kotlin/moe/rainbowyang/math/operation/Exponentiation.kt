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
