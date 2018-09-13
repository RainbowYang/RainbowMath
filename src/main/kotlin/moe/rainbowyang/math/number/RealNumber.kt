package moe.rainbowyang.math.number

import kotlin.math.*

/**
 * 实数，以[Double]为底层
 * @author Rainbow Yang
 */
class RealNumber(val value: Double) : Number<RealNumber>, Comparable<RealNumber> {
    constructor(value: kotlin.Number) : this(value.toDouble())

    companion object {
        val ZERO = RealNumber(0)
    }

    override fun plus(other: RealNumber) = RealNumber(this.value + other.value)
    override fun times(other: RealNumber) = RealNumber(this.value * other.value)
    override fun unaryMinus() = RealNumber(-value)
    override fun reciprocal() = RealNumber(1 / value)

    override operator fun compareTo(other: RealNumber) = value.compareTo(other.value)

    override fun toString() = "Re-($value)"
}

object RealNumbeMath : NumberMath<RealNumber> {
    override val ZERO = RealNumber.ZERO

    override fun sin(num: RealNumber) = RealNumber(sin(num.value))
    override fun cos(num: RealNumber) = RealNumber(cos(num.value))

    override fun sqrt(num: RealNumber) = RealNumber(sqrt(num.value))

    fun atan2(y: RealNumber, x: RealNumber) = RealNumber(atan2(y.value, x.value))
}