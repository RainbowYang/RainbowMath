package moe.rainbowyang.math.number

import moe.rainbowyang.math.number.Quaternion.Math.asQuaternion
import moe.rainbowyang.math.number.RealNumber.Math.lengthOf
import moe.rainbowyang.math.number.RealNumber.Math.sumOfSquare
import moe.rainbowyang.math.operation.HyperOperation

/**
 *  四元数（a+bi+cj+dk）
 * @author Rainbow Yang
 */
data class Quaternion(val a: RealNumber, val b: RealNumber, val c: RealNumber, val d: RealNumber) :
        Number,
        HyperOperation<Quaternion> {

    val modulus = lengthOf(a, b, c, d)

    override operator fun plus(other: Quaternion) =
            Quaternion(a + other.a, b + other.b, c + other.c, d + other.d)

    override operator fun unaryMinus() = Quaternion(-a, -b, -c, -d)

    override operator fun times(other: Quaternion): Quaternion {
        val (a, b, c, d) = this
        val (t, x, y, z) = other
        return Quaternion(a * t - b * x - c * y - d * z, b * t + a * x + c * z - d * y,
                c * t + a * y + b * z - d * x, d * t + z * a + c * x - b * y)
    }

    override fun reciprocal() = this.conjugate() / sumOfSquare(a, b, c, d).asQuaternion()

    /** 共轭 */
    fun conjugate() = Quaternion(a, -b, -c, -d)

    override fun toString() = "$a" +
            "${if (b >= RealNumber.ZERO) "+" else ""}${b}i" +
            "${if (c >= RealNumber.ZERO) "+" else ""}${c}j" +
            "${if (d >= RealNumber.ZERO) "+" else ""}${d}k"

    object Math {
        fun RealNumber.asQuaternion() = Quaternion(this, RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO)
    }
}