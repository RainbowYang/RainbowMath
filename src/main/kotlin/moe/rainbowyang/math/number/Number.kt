package moe.rainbowyang.math.number

/**
 * 定义为数学上的所有的数的抽象类
 * @author Rainbow Yang
 */
interface AbstractNumber<N : AbstractNumber<N>> {
    operator fun plus(other: N): N
    operator fun times(other: N): N
}

interface Number<N : Number<N>> : AbstractNumber<N> {
    operator fun unaryMinus(): N
    fun reciprocal(): N

    operator fun minus(other: Number<N>): N = this + (-other)
    operator fun div(other: Number<N>): N = this * (other.reciprocal())
}

interface NumberMathExpansion<T : Number<T>, O : Number<O>, Out : Number<Out>> {
    operator fun T.plus(other: O): Out
    operator fun T.times(other: O): Out

    operator fun T.minus(other: O): Out = this + (-other)
    operator fun T.div(other: O): Out = this * (other.reciprocal())
}

interface NumberMath<N : Number<N>> {
    val ZERO: N

    fun sin(num: N): N
    fun cos(num: N): N
    fun tan(num: N): N = sin(num) / cos(num)

    fun sqrt(num: N): N

    fun lengthOf(vararg nums: N) = nums.asList().lengthOf()
    fun List<N>.lengthOf() = sqrt(sumOfSquare())
    fun sumOfSquare(vararg nums: N) = nums.asList().sumOfSquare()
    fun List<N>.sumOfSquare() = fold(ZERO) { sum, next -> sum + next * next }
}