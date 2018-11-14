package moe.rainbowyang.math.operation

/**
 * 抽象乘法运算
 * @author: Rainbow Yang
 * @create: 2018-11-12 11:26
 **/
interface Multiplication<T> : Operation<T> {
    
    operator fun times(other: T): T
    operator fun div(other: T): T = times(reciprocal())
    fun reciprocal(): T
    
}