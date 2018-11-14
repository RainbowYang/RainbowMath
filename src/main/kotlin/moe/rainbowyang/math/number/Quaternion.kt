package moe.rainbowyang.math.number

import moe.rainbowyang.math.matrix.Matrix
import moe.rainbowyang.math.operation.*

/**
 * 四元数（a+bi+cj+dk）
 * @author Rainbow Yang
 */
data class Quaternion(val a: Real, val b: Real, val c: Real, val d: Real) : AbstractNumber<Quaternion> {
    
    val modulus = lengthOf(a, b, c, d)
    val scalar = a
    val vecter = Quaternion(Real.ZERO, b, c, d)
    
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
    
    override fun reciprocal() = this.conjugate() / sumOfSquare(a, b, c, d).asQuaternion()
    
    override fun exp(): Quaternion =
            exp(a).asQuaternion() * (cos(vecter.modulus).asQuaternion()
                    + sgn(vecter) * sin(vecter.modulus).asQuaternion())
    
    override fun ln(): Quaternion =
            ln(modulus).asQuaternion() + sgn(vecter) * arg(this).asQuaternion()
    
    override fun sin() =
            sin(a).asQuaternion() * cosh(vecter.modulus).asQuaternion() +
                    cos(a).asQuaternion() * sgn(vecter) * sinh(vecter.modulus).asQuaternion()
    
    override fun cos() =
            cos(a).asQuaternion() * cosh(vecter.modulus).asQuaternion() -
                    sin(a).asQuaternion() * sgn(vecter) * sinh(vecter.modulus).asQuaternion()
    
    override fun sinh() =
            sinh(a).asQuaternion() * cos(vecter.modulus).asQuaternion() +
                    cosh(a).asQuaternion() * sgn(vecter) * sin(vecter.modulus).asQuaternion()
    
    override fun cosh() =
            cosh(a).asQuaternion() * cos(vecter.modulus).asQuaternion() -
                    sinh(a).asQuaternion() * sgn(vecter) * sin(vecter.modulus).asQuaternion()
    
    /** 共轭 */
    fun conjugate() = Quaternion(a, -b, -c, -d)
    
    override fun toString() = "$a" +
            "${if (b >= Real.ZERO) "+" else ""}${b}i" +
            "${if (c >= Real.ZERO) "+" else ""}${c}j" +
            "${if (d >= Real.ZERO) "+" else ""}${d}k"
    
}
