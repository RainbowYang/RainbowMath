package moe.rainbowyang.math.number

import moe.rainbowyang.math.lengthOf
import moe.rainbowyang.math.sumOfSquare

/**
 *  四元数（a+bi+cj+dk）
 * @author Rainbow Yang
 */
data class Quaternion(val a: Double, val b: Double, val c: Double, val d: Double) {
    constructor(a: Number, b: Number, c: Number, d: Number) :
            this(a.toDouble(), b.toDouble(), c.toDouble(), d.toDouble())


    /** 模长 */
    val modulus: Double = lengthOf(a, b, c, d)

    /** 倒数 */
    fun reciprocal() = this.conjugate() / sumOfSquare(a, b, c, d)

    /** 共轭 */
    fun conjugate() = Quaternion(a, -b, -c, -d)

    operator fun plus(other: Number) = copy(a = a + other.toDouble())
    operator fun plus(other: Quaternion) =
            Quaternion(a + other.a, b + other.b, c + other.c, +other.d)

    operator fun minus(other: Number) = this + (-other.toDouble())
    operator fun minus(other: Quaternion) = this + (-other)
    operator fun unaryMinus() = Quaternion(-a, -b, -c, -d)

    operator fun times(other: Number) = times(other.toDouble())
    operator fun times(other: Double) = Quaternion(other * a, other * b, other * c, other * d)
    operator fun times(other: Quaternion): Quaternion {
        val (a, b, c, d) = this
        val (t, x, y, z) = other
        return Quaternion(a * t - b * x - c * y - d * z, b * t + a * x + c * z - d * y,
                c * t + a * y + b * z - d * x, d * t + z * a + c * x - b * y)
    }

    operator fun div(other: Number) = this * (1 / other.toDouble())
    operator fun div(other: Quaternion) = this * other.reciprocal()

    override fun toString() = "$a" +
            "${if (b >= 0) "+" else ""}${b}i" +
            "${if (c >= 0) "+" else ""}${c}j" +
            "${if (d >= 0) "+" else ""}${d}k"
}