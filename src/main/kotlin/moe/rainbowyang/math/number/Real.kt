package moe.rainbowyang.math.number

import kotlin.math.*

/**
 * 实数
 * @author Rainbow Yang
 */
class Real(val value: Double) : AbstractNumber<Real>, Comparable<Real> {
    constructor(value: Number) : this(value.toDouble())

    companion object {
        val ZERO = Real(0)
    }

    override fun plus(other: Real) = Real(this.value + other.value)
    override fun times(other: Real) = Real(this.value * other.value)
    override fun unaryMinus() = Real(-value)
    override fun reciprocal() = Real(1 / value)

    override fun sin() = Real(sin(value))
    override fun cos() = Real(cos(value))

    override fun sinh() = Real(sinh(value))
    override fun cosh() = Real(cosh(value))

    override fun exp() = Real(exp(value))
    override fun ln() = Real(ln(value))

    override operator fun compareTo(other: Real) = value.compareTo(other.value)

    override fun toString() = value.toString()

    object Math {
        fun kotlin.Number.asReal() = Real(this)

        fun sqrt(num: Real) = Real(sqrt(num.value))

        fun atan2(y: Real, x: Real) = Real(atan2(y.value, x.value))

        fun lengthOf(vararg nums: Real) = nums.asList().lengthOf()
        fun List<Real>.lengthOf() = sqrt(sumOfSquare())
        fun sumOfSquare(vararg nums: Real) = nums.asList().sumOfSquare()
        fun List<Real>.sumOfSquare() =
                fold(ZERO) { sum, next -> sum + next * next }
    }
}


