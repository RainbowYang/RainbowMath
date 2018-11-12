package moe.rainbowyang.math.operation

/**
 * 抽象加法运算
 * @author: Rainbow Yang
 * @create: 2018-11-12 11:26
 **/
interface Addition<T> : Operation<T> {
    operator fun plus(other: T): T
    operator fun minus(other: T): T = plus(unaryMinus())
    operator fun unaryMinus(): T
}