package moe.rainbowyang.math.operation

/**
 * 抽象乘法运算
 * @author: Rainbow Yang
 * @create: 2018-11-12 11:26
 **/
interface Multiplication<This> : Operation<This> {
    operator fun times(other: This): This
    operator fun div(other: This): This = times(reciprocal())
    fun reciprocal(): This
}