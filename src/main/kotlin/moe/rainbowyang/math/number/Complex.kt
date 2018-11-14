package moe.rainbowyang.math.number

import moe.rainbowyang.math.matrix.Matrix
import moe.rainbowyang.math.number.Complex.Normal.Companion.withI
import moe.rainbowyang.math.operation.cos
import moe.rainbowyang.math.operation.exp
import moe.rainbowyang.math.operation.ln
import moe.rainbowyang.math.operation.sin

/**
 * 复数
 * @author Rainbow Yang
 */
abstract class Complex : AbstractNumber<Complex> {
    
    companion object {
        val ZERO: Complex = Real.ZERO withI Real.ZERO
    }
    
    open val real: Real get() = asNormal().real
    open val imag: Real get() = asNormal().imag
    open val modulus: Real get() = asExp().modulus
    open val argument: Real get() = asExp().argument
    
    abstract fun asNormal(): Normal
    abstract fun asExp(): AsExp
    
    abstract fun conjugate(): Complex
    
    abstract fun asMatrix(): Matrix
    
    /**
     * 复数表示为a+bi
     */
    class Normal(override val real: Real, override val imag: Real) : Complex() {
        
        companion object {
            infix fun Real.withI(imag: Real) = Normal(this, imag)
        }
        
        override fun asMatrix() = Matrix(listOf(real, -imag), listOf(imag, real))
        
        override fun asNormal() = this
        override fun asExp() = AsExp(lengthOf(real, imag), atan2(real, imag))
        
        override fun plus(other: Complex) = real + other.real withI imag + other.imag
        override fun times(other: Complex) = asExp() * other
        
        override fun unaryMinus() = -real withI -imag
        override fun reciprocal() = asExp().reciprocal()
        
        override fun conjugate(): Complex = real withI -imag
        
        override fun exp(): Complex = exp(real).asComplex() * cis(imag)
        override fun ln(): Complex = ln(modulus) withI argument
        
        override fun sin(): Complex = sin(real).asComplex() * cosI(imag) + sinI(imag) * cos(real).asComplex()
        override fun cos(): Complex = cos(real).asComplex() * cosI(imag) - sinI(imag) * sin(real).asComplex()
        
        override fun sinh(): Complex = (exp() - unaryMinus().exp()) / 2.asComplex()
        override fun cosh(): Complex = (exp() + unaryMinus().exp()) / 2.asComplex()
        
        private fun cis(num: Real): Complex = cos(num) withI sin(num)
        private fun sinI(num: Real): Complex = Real.ZERO withI (exp(num) - exp(-num)) / 2.asReal()
        private fun cosI(num: Real): Complex = (exp(num) + exp(-num)) / 2.asReal() withI Real.ZERO
        
        override fun toString() = "$real ${if (imag >= Real.ZERO) "+" else ""} ${imag}i"
        
    }
    
    class AsExp(override val modulus: Real, override val argument: Real) : Complex() {
        
        companion object {
            infix fun Real.expI(argument: Real) = AsExp(this, argument)
        }
        
        override fun asMatrix() = asNormal().asMatrix()
        
        override fun asNormal() = Normal(modulus * cos(argument), modulus * sin(argument))
        override fun asExp() = this
        
        override fun plus(other: Complex) = this.asNormal() + other
        override fun times(other: Complex) = modulus * other.modulus expI argument + other.argument
        
        override fun unaryMinus() = asNormal().unaryMinus()
        override fun reciprocal() = modulus.reciprocal() expI -argument
        
        override fun conjugate(): Complex = modulus expI -argument
        
        override fun exp(): Complex = asExp().exp()
        override fun ln(): Complex = asExp().ln()
        
        override fun sin(): Complex = asExp().sin()
        override fun cos(): Complex = asExp().cos()
        
        override fun sinh(): Complex = asExp().sinh()
        override fun cosh(): Complex = asExp().cosh()
        
        override fun toString() = "$modulus * exp($argument)"
        
    }
    
}
