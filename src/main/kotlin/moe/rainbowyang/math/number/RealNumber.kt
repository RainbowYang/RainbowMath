package moe.rainbowyang.math.number

import moe.rainbowyang.math.operation.*
import kotlin.math.*

/**
 * 实数
 * @author Rainbow Yang
 */
class RealNumber(val value: Double) :
        Number,
        Comparable<RealNumber>,
        Addition<RealNumber>,
        Multiplication<RealNumber>,
        Exponentiation<RealNumber>,
        TrigonometricFunctions<RealNumber>,
        HyperbolicFunction<RealNumber> {
    constructor(value: kotlin.Number) : this(value.toDouble())

    companion object {
        val ZERO = RealNumber(0)
    }

    override fun plus(other: RealNumber) = RealNumber(this.value + other.value)
    override fun times(other: RealNumber) = RealNumber(this.value * other.value)
    override fun unaryMinus() = RealNumber(-value)
    override fun reciprocal() = RealNumber(1 / value)

    override fun sin() = RealNumber(sin(value))
    override fun cos() = RealNumber(cos(value))

    override fun sinh() = RealNumber(sinh(value))
    override fun cosh() = RealNumber(cosh(value))

    override fun exp() = RealNumber(exp(value))
    override fun ln() = RealNumber(ln(value))

    override operator fun compareTo(other: RealNumber) = value.compareTo(other.value)

    override fun toString() = value.toString()

    object Math {
        fun kotlin.Number.asReal() = RealNumber(this)

        fun sqrt(num: RealNumber) = RealNumber(sqrt(num.value))

        fun atan2(y: RealNumber, x: RealNumber) = RealNumber(atan2(y.value, x.value))

        fun lengthOf(vararg nums: RealNumber) = nums.asList().lengthOf()
        fun List<RealNumber>.lengthOf() = sqrt(sumOfSquare())
        fun sumOfSquare(vararg nums: RealNumber) = nums.asList().sumOfSquare()
        fun List<RealNumber>.sumOfSquare() =
                fold(ZERO) { sum, next -> sum + next * next }
    }
}


