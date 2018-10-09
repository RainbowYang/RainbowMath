package moe.rainbowyang.math.number

import moe.rainbowyang.math.number.ComplexNumber.Math.asComplex
import moe.rainbowyang.math.number.ComplexNumber.Normal.Companion.withI
import moe.rainbowyang.math.number.RealNumber.Math.asReal
import moe.rainbowyang.math.number.RealNumber.Math.atan2
import moe.rainbowyang.math.number.RealNumber.Math.cos
import moe.rainbowyang.math.number.RealNumber.Math.exp
import moe.rainbowyang.math.number.RealNumber.Math.lengthOf
import moe.rainbowyang.math.number.RealNumber.Math.ln
import moe.rainbowyang.math.number.RealNumber.Math.sin
import moe.rainbowyang.math.operation.HyperOperation
import moe.rainbowyang.math.operation.HyperbolicFunction
import moe.rainbowyang.math.operation.LogAndExp
import moe.rainbowyang.math.operation.TrigonometricFunctions

/**
 * 复数
 * @author Rainbow Yang
 */
abstract class ComplexNumber :
        Number,
        HyperOperation<ComplexNumber>,
        TrigonometricFunctions<ComplexNumber>,
        HyperbolicFunction<ComplexNumber>,
        LogAndExp<ComplexNumber> {

    companion object {
        val ZERO: ComplexNumber = RealNumber.ZERO withI RealNumber.ZERO
    }

    open val real: RealNumber get() = asNormal().real
    open val imag: RealNumber get() = asNormal().imag
    open val modulus: RealNumber get() = asExp().modulus
    open val argument: RealNumber get() = asExp().argument

    abstract fun asNormal(): Normal
    abstract fun asExp(): AsExp

    abstract fun conjugate(): ComplexNumber

    /**
     * 复数表示为a+bi
     */
    class Normal(override val real: RealNumber, override val imag: RealNumber) : ComplexNumber() {
        companion object {
            infix fun RealNumber.withI(imag: RealNumber) = Normal(this, imag)
        }

        override fun asNormal() = this
        override fun asExp() = AsExp(lengthOf(real, imag), atan2(real, imag))

        override fun plus(other: ComplexNumber) = real + other.real withI imag + other.imag
        override fun times(other: ComplexNumber) = asExp() * other

        override fun unaryMinus() = -real withI -imag
        override fun reciprocal() = asExp().reciprocal()

        override fun conjugate(): ComplexNumber = real withI -imag

        override fun exp(): ComplexNumber = exp(real).asComplex() * cis(imag)
        override fun ln(): ComplexNumber = ln(modulus) withI argument

        override fun sin(): ComplexNumber = sin(real).asComplex() * cosI(imag) + sinI(imag) * cos(real).asComplex()
        override fun cos(): ComplexNumber = cos(real).asComplex() * cosI(imag) - sinI(imag) * sin(real).asComplex()

        override fun sinh(): ComplexNumber = (exp() - unaryMinus().exp()) / 2.asComplex()
        override fun cosh(): ComplexNumber = (exp() + unaryMinus().exp()) / 2.asComplex()

        private fun cis(num: RealNumber): ComplexNumber = cos(num) withI sin(num)
        private fun sinI(num: RealNumber): ComplexNumber = RealNumber.ZERO withI (exp(num) - exp(-num)) / 2.asReal()
        private fun cosI(num: RealNumber): ComplexNumber = (exp(num) + exp(-num)) / 2.asReal() withI RealNumber.ZERO

        override fun toString() = "$real ${if (imag >= RealNumber.ZERO) "+" else ""} ${imag}i"
    }

    class AsExp(override val modulus: RealNumber, override val argument: RealNumber) : ComplexNumber() {
        companion object {
            infix fun RealNumber.expI(argument: RealNumber) = AsExp(this, argument)
        }

        override fun asNormal() = Normal(modulus * cos(argument), modulus * sin(argument))
        override fun asExp() = this

        override fun plus(other: ComplexNumber) = this.asNormal() + other
        override fun times(other: ComplexNumber) = modulus * other.modulus expI argument + other.argument

        override fun unaryMinus() = asNormal().unaryMinus()
        override fun reciprocal() = modulus.reciprocal() expI -argument

        override fun conjugate(): ComplexNumber = modulus expI -argument

        override fun exp(): ComplexNumber = asExp().exp()
        override fun ln(): ComplexNumber = asExp().ln()

        override fun sin(): ComplexNumber = asExp().sin()
        override fun cos(): ComplexNumber = asExp().cos()

        override fun sinh(): ComplexNumber = asExp().sinh()
        override fun cosh(): ComplexNumber = asExp().cosh()

        override fun toString() = "$modulus * exp($argument)"
    }

    object Math : TrigonometricFunctions.Math<ComplexNumber>, HyperbolicFunction.Math<ComplexNumber>, LogAndExp.Math<ComplexNumber> {
        fun RealNumber.asComplex() = this withI RealNumber.ZERO
        fun kotlin.Number.asComplex() = this.asReal() withI RealNumber.ZERO
    }
}