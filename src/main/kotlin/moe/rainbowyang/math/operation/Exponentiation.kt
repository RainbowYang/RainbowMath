package moe.rainbowyang.math.operation

/**
 * 抽象幂运算
 * 依赖[Multiplication]
 * @author: Rainbow Yang
 * @create: 2018-11-12 12:03
 **/
interface Exponentiation<This> : Operation<This>
        where This : Exponentiation<This>, This : Multiplication<This> {
    infix fun power(other: This): This = (this.ln() * other).exp()
    infix fun log(other: This): This = other.ln() / this.ln()

    fun exp(): This
    fun ln(): This
}
