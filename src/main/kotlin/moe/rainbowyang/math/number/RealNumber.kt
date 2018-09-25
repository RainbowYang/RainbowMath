package moe.rainbowyang.math.number


import moe.rainbowyang.math.operation.HyperOperation
import moe.rainbowyang.math.operation.LogAndExp
import moe.rainbowyang.math.operation.TrigonometricFunctions
import kotlin.math.*

/**
 * 实数，以[Double]为底层
 * @author Rainbow Yang
 */
class RealNumber(val value: Double) :
        Number,
        Comparable<RealNumber>,
        HyperOperation<RealNumber>,
        TrigonometricFunctions<RealNumber>,
        LogAndExp<RealNumber> {
    constructor(value: kotlin.Number) : this(value.toDouble())

    companion object {
        val ZERO = RealNumber(0)
        fun kotlin.Number.asRealNumber() = RealNumber(this)
    }

    override fun kotlin.Number.asThis() = RealNumber(this)

    override fun plus(other: RealNumber) = RealNumber(this.value + other.value)
    override fun times(other: RealNumber) = RealNumber(this.value * other.value)
    override fun unaryMinus() = RealNumber(-value)
    override fun reciprocal() = RealNumber(1 / value)

    override fun sin() = RealNumber(sin(value))
    override fun cos() = RealNumber(cos(value))

    override fun exp() = RealNumber(exp(value))
    override fun ln() = RealNumber(ln(value))

    override operator fun compareTo(other: RealNumber) = value.compareTo(other.value)

    override fun toString() = value.toString()

    object Math : HyperOperation.Math<RealNumber>,
            TrigonometricFunctions.Math<RealNumber>,
            LogAndExp.Math<RealNumber> {
        override val ZERO: RealNumber
            get() = RealNumber.ZERO

        fun sqrt(num: RealNumber) = RealNumber(sqrt(num.value))

        fun atan2(y: RealNumber, x: RealNumber) = RealNumber(atan2(y.value, x.value))

        fun lengthOf(vararg nums: RealNumber) = nums.asList().lengthOf()
        fun List<RealNumber>.lengthOf() = sqrt(sumOfSquare())
        fun sumOfSquare(vararg nums: RealNumber) = nums.asList().sumOfSquare()
        fun List<RealNumber>.sumOfSquare() =
                fold(ZERO) { sum, next -> sum + next * next }
    }
}


