package moe.rainbowyang.math.number

/**
 * 定义为数学上的所有的数的抽象类
 * @author Rainbow Yang
 */
abstract class AbstractNumber<N : AbstractNumber<N>> {
    abstract operator fun plus(other: N): N
    abstract operator fun times(other: N): N
}

abstract class Number<N : AbstractNumber<N>> : AbstractNumber<N>() {
    abstract operator fun unaryMinus(): N
    abstract fun reciprocal(): N

    operator fun minus(other: Number<N>): N = this + (-other)
    operator fun div(other: Number<N>): N = this + (other.reciprocal())
}