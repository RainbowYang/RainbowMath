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