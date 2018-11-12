package moe.rainbowyang.math.operation

/**
 * 抽象加法运算
 * @author: Rainbow Yang
 * @create: 2018-11-12 11:26
 **/
interface Addition<This> : Operation<This> {
    operator fun plus(other: This): This
    operator fun minus(other: This): This = plus(unaryMinus())
    operator fun unaryMinus(): This
}