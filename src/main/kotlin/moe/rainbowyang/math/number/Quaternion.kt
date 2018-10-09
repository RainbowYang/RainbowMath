package moe.rainbowyang.math.number

import moe.rainbowyang.math.number.Quaternion.Math.arg
import moe.rainbowyang.math.number.Quaternion.Math.asQuaternion
import moe.rainbowyang.math.number.Quaternion.Math.sgn
import moe.rainbowyang.math.number.RealNumber.Math.lengthOf
import moe.rainbowyang.math.number.RealNumber.Math.cos
import moe.rainbowyang.math.number.RealNumber.Math.exp
import moe.rainbowyang.math.number.RealNumber.Math.asReal
import moe.rainbowyang.math.number.RealNumber.Math.cosh
import moe.rainbowyang.math.number.RealNumber.Math.ln
import moe.rainbowyang.math.number.RealNumber.Math.sin
import moe.rainbowyang.math.number.RealNumber.Math.sinh
import moe.rainbowyang.math.number.RealNumber.Math.sumOfSquare
import moe.rainbowyang.math.operation.HyperOperation
import moe.rainbowyang.math.operation.HyperbolicFunction
import moe.rainbowyang.math.operation.LogAndExp
import moe.rainbowyang.math.operation.TrigonometricFunctions
import kotlin.math.acos

/**
 *  四元数（a+bi+cj+dk）
 * @author Rainbow Yang
 */
data class Quaternion(val a: RealNumber, val b: RealNumber, val c: RealNumber, val d: RealNumber) :
        Number,
        HyperOperation<Quaternion>,
        TrigonometricFunctions<Quaternion>,
        HyperbolicFunction<Quaternion>,
        LogAndExp<Quaternion> {

    val modulus = lengthOf(a, b, c, d)
    val scalar = a
    val vecter = Quaternion(RealNumber.ZERO, b, c, d)

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

    override fun exp(): Quaternion = exp(a).asQuaternion() * (cos(vecter.modulus).asQuaternion() + sgn(vecter) * sin(vecter.modulus).asQuaternion())
    override fun ln(): Quaternion = ln(modulus).asQuaternion() + sgn(vecter) * arg(this).asQuaternion()

    override fun sin() = sin(a).asQuaternion() * cosh(vecter.modulus).asQuaternion() +
            cos(a).asQuaternion() * sgn(vecter) * sinh(vecter.modulus).asQuaternion()

    override fun cos() = cos(a).asQuaternion() * cosh(vecter.modulus).asQuaternion() -
            sin(a).asQuaternion() * sgn(vecter) * sinh(vecter.modulus).asQuaternion()

    override fun sinh() = sinh(a).asQuaternion() * cos(vecter.modulus).asQuaternion() +
            cosh(a).asQuaternion() * sgn(vecter) * sin(vecter.modulus).asQuaternion()

    override fun cosh() = cosh(a).asQuaternion() * cos(vecter.modulus).asQuaternion() -
            sinh(a).asQuaternion() * sgn(vecter) * sin(vecter.modulus).asQuaternion()

    /** 共轭 */
    fun conjugate() = Quaternion(a, -b, -c, -d)

    override fun toString() = "$a" +
            "${if (b >= RealNumber.ZERO) "+" else ""}${b}i" +
            "${if (c >= RealNumber.ZERO) "+" else ""}${c}j" +
            "${if (d >= RealNumber.ZERO) "+" else ""}${d}k"

    object Math {
        fun kotlin.Number.asQuaternion() = Quaternion(this.asReal(), RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO)
        fun RealNumber.asQuaternion() = Quaternion(this, RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO)
        fun ComplexNumber.asQuaternion() = Quaternion(real, imag, RealNumber.ZERO, RealNumber.ZERO)

        fun inner(p: Quaternion, q: Quaternion) = q.a * p.a + q.b * p.b + q.c * p.c + q.d * p.d
        fun outer(p: Quaternion, q: Quaternion) = (p.conjugate() * q - q.conjugate() * p) / 2.asQuaternion()
        fun even(p: Quaternion, q: Quaternion) = (p * q + q * p) / 2.asQuaternion()
        fun odd(p: Quaternion, q: Quaternion) = (p * q - q * p) / 2.asQuaternion()

        fun sgn(p: Quaternion) = p / p.modulus.asQuaternion()
        fun arg(p: Quaternion) = acos((p.a / p.modulus).value)

    }
}