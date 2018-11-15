package moe.rainbowyang.math.number

import moe.rainbowyang.math.matrix.Matrix
import moe.rainbowyang.math.operation.*

/**
 * 四元数（a+bi+cj+dk）
 * @author Rainbow Yang
 */
data class Quaternion(val a: Real = Real.ZERO,
                      val b: Real = Real.ZERO,
                      val c: Real = Real.ZERO,
                      val d: Real = Real.ZERO) : AbstractNumber<Quaternion> {
    
    constructor(a: Number) : this(a.toReal())
    
    val modulus = lengthOf(a, b, c, d)
    val scalar = a
    val vecter get() = Quaternion(Real.ZERO, b, c, d)
    
    fun asMatrix() =
            Matrix(listOf(a, d, -c, b), listOf(-d, a, -b, -c), listOf(c, b, a, -d), listOf(-b, c, d, a))
    
    override operator fun plus(other: Quaternion) =
            Quaternion(a + other.a, b + other.b, c + other.c, d + other.d)
    
    override operator fun unaryMinus() = Quaternion(-a, -b, -c, -d)
    
    override operator fun times(other: Quaternion): Quaternion {
        val (a, b, c, d) = this
        val (t, x, y, z) = other
        return Quaternion(a * t - b * x - c * y - d * z, b * t + a * x + c * z - d * y,
                c * t + a * y + b * z - d * x, d * t + z * a + c * x - b * y)
    }
    
    override fun reciprocal() = this.conjugate() / sumOfSquare(a, b, c, d).toQuaternion()
    
    override fun exp(): Quaternion =
            exp(a).toQuaternion() * (cos(vecter.modulus).toQuaternion()
                    + sgn(vecter) * sin(vecter.modulus).toQuaternion())
    
    override fun ln(): Quaternion =
            ln(modulus).toQuaternion() + sgn(vecter) * arg(this).toQuaternion()
    
    override fun sin() =
            sin(a).toQuaternion() * cosh(vecter.modulus).toQuaternion() +
                    cos(a).toQuaternion() * sgn(vecter) * sinh(vecter.modulus).toQuaternion()
    
    override fun cos() =
            cos(a).toQuaternion() * cosh(vecter.modulus).toQuaternion() -
                    sin(a).toQuaternion() * sgn(vecter) * sinh(vecter.modulus).toQuaternion()
    
    override fun sinh() =
            sinh(a).toQuaternion() * cos(vecter.modulus).toQuaternion() +
                    cosh(a).toQuaternion() * sgn(vecter) * sin(vecter.modulus).toQuaternion()
    
    override fun cosh() =
            cosh(a).toQuaternion() * cos(vecter.modulus).toQuaternion() -
                    sinh(a).toQuaternion() * sgn(vecter) * sin(vecter.modulus).toQuaternion()
    
    /** 共轭 */
    fun conjugate() = Quaternion(a, -b, -c, -d)
    
    override fun toString() = "$a" +
            "${if (b >= Real.ZERO) "+" else ""}${b}i" +
            "${if (c >= Real.ZERO) "+" else ""}${c}j" +
            "${if (d >= Real.ZERO) "+" else ""}${d}k"
    
}
