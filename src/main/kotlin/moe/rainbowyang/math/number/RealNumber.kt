package moe.rainbowyang.math.number

/**
 * 实数，以[Double]为底层
 * @author Rainbow Yang
 */
class RealNumber(val value: Double) : Number<RealNumber>(),Comparable<RealNumber> {
    constructor(value: kotlin.Number) : this(value.toDouble())

    override fun plus(other: RealNumber) = RealNumber(this.value + other.value)
    override fun times(other: RealNumber) = RealNumber(this.value * other.value)
    override fun unaryMinus() = RealNumber(-value)
    override fun reciprocal() = RealNumber(1 / value)

    override operator fun compareTo(other: RealNumber) = value.compareTo(other.value)

    override fun toString() = "Re-($value)"
}