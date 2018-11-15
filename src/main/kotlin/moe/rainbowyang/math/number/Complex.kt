package moe.rainbowyang.math.number

import moe.rainbowyang.math.matrix.Matrix
import moe.rainbowyang.math.operation.cos
import moe.rainbowyang.math.operation.exp
import moe.rainbowyang.math.operation.ln
import moe.rainbowyang.math.operation.sin

/**
 * 复数
 * @author Rainbow Yang
 */
class Complex(val real: Real, val imag: Real = Real.ZERO) : AbstractNumber<Complex> {
    
    constructor(real: Number) : this(real.toReal())
    constructor(real: Number, imag: Number) : this(real.toReal(), imag.toReal())
    
    companion object {
        val ZERO: Complex = Complex(Real.ZERO)
    }
    
    val modulus: Real = lengthOf(real, imag)
    val argument: Real get() = atan2(imag, real)
    
    fun asMatrix() = Matrix(listOf(real, -imag), listOf(imag, real))
    
    override fun plus(other: Complex) = real + other.real withI imag + other.imag
    override fun times(other: Complex) = modulus * other.modulus expI argument + other.argument
    
    override fun unaryMinus() = -real withI -imag
    override fun reciprocal() = modulus.reciprocal() expI -argument
    
    fun conjugate(): Complex = real withI -imag
    
    override fun exp(): Complex = exp(real).toComplex() * cis(imag)
    override fun ln(): Complex = ln(modulus) withI argument
    
    override fun sin(): Complex = sin(real).toComplex() * cosI(imag) + sinI(imag) * cos(real).toComplex()
    override fun cos(): Complex = cos(real).toComplex() * cosI(imag) - sinI(imag) * sin(real).toComplex()
    
    override fun sinh(): Complex = (exp() - unaryMinus().exp()) / 2
    override fun cosh(): Complex = (exp() + unaryMinus().exp()) / 2
    
    private fun cis(num: Real): Complex = cos(num) withI sin(num)
    private fun sinI(num: Real): Complex = Real.ZERO withI (exp(num) - exp(-num)) / 2
    private fun cosI(num: Real): Complex = (exp(num) + exp(-num)) / 2 withI Real.ZERO
    
    override fun toString() = "$real ${if (imag >= Real.ZERO) "+" else ""} ${imag}i"
    
}
