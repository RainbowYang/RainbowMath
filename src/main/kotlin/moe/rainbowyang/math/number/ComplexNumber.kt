package moe.rainbowyang.math.number

import moe.rainbowyang.math.number.RealNumber.Math.asThis
import moe.rainbowyang.math.number.RealNumber.Math.atan2
import moe.rainbowyang.math.number.RealNumber.Math.cos
import moe.rainbowyang.math.number.RealNumber.Math.lengthOf
import moe.rainbowyang.math.number.RealNumber.Math.ln
import moe.rainbowyang.math.number.RealNumber.Math.sin
import moe.rainbowyang.math.number.RealNumber.Math.exp
import moe.rainbowyang.math.operation.HyperOperation
import moe.rainbowyang.math.operation.LogAndExp
import moe.rainbowyang.math.operation.TrigonometricFunctions
import moe.rainbowyang.math.number.ComplexNumber.Normal.Companion.withI
import moe.rainbowyang.math.number.ComplexNumber.Math.plus
import moe.rainbowyang.math.number.ComplexNumber.Math.minus
import moe.rainbowyang.math.number.ComplexNumber.Math.times

/**
 * 复数
 * @author Rainbow Yang
 */
abstract class ComplexNumber :
        Number<ComplexNumber>,
        HyperOperation.WithOther<ComplexNumber, RealNumber>,
        TrigonometricFunctions<ComplexNumber>,
        LogAndExp.WithOther<ComplexNumber, RealNumber> {

    companion object {
        val ZERO: ComplexNumber = 0 withI 0
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
            infix fun kotlin.Number.withI(imag: kotlin.Number) = this.asThis() withI RealNumber(imag)
            infix fun kotlin.Number.withI(imag: RealNumber) = this.asThis() withI imag
            infix fun RealNumber.withI(imag: kotlin.Number) = this withI imag.asThis()
        }

        override fun asNormal() = this
        override fun asExp() = AsExp(lengthOf(real, imag), atan2(real, imag))

        override fun kotlin.Number.asThis(): ComplexNumber = this withI 0
        override fun RealNumber.asThis(): ComplexNumber = this withI 0

        override fun plus(other: ComplexNumber) = real + other.real withI imag + other.imag
        override fun times(other: ComplexNumber) = asExp() * other

        override fun unaryMinus() = -real withI -imag
        override fun reciprocal() = asExp().reciprocal()

        override fun conjugate(): ComplexNumber = real withI -imag

        override fun exp(): ComplexNumber = exp(real) * cis(imag)
        override fun ln(): ComplexNumber = ln(modulus) withI argument

        override fun sin(): ComplexNumber = sin(real) * cosI(imag) + sinI(imag) * cos(real)
        override fun cos(): ComplexNumber = cos(real) * cosI(imag) - sinI(imag) * sin(real)

        private fun cis(num: RealNumber) = cos(num) withI sin(num)
        private fun sinI(num: RealNumber) = 0 withI (exp(num) - exp(-num)) / 2
        private fun cosI(num: RealNumber) = (exp(num) + exp(-num)) / 2

        override fun toString() = "$real ${if (imag >= RealNumber.ZERO) "+" else ""} ${imag}i"
    }

    class AsExp(override val modulus: RealNumber, override val argument: RealNumber) : ComplexNumber() {
        companion object {
            infix fun RealNumber.expI(argument: RealNumber) = AsExp(this, argument)
            infix fun kotlin.Number.expI(argument: kotlin.Number) = asThis() expI argument.asThis()
            infix fun kotlin.Number.expI(argument: RealNumber) = asThis() expI argument
            infix fun RealNumber.expI(argument: kotlin.Number) = this expI argument.asThis()
        }

        override fun asNormal() = Normal(modulus * cos(argument), modulus * sin(argument))
        override fun asExp() = this

        override fun kotlin.Number.asThis(): ComplexNumber = this expI 0
        override fun RealNumber.asThis(): ComplexNumber = this expI 0

        override fun plus(other: ComplexNumber) = this.asNormal() + other
        override fun times(other: ComplexNumber) = modulus * other.modulus expI argument + other.argument

        override fun unaryMinus() = asNormal().unaryMinus()
        override fun reciprocal() = modulus.reciprocal() expI -argument

        override fun conjugate(): ComplexNumber = modulus expI -argument

        override fun exp(): ComplexNumber = asExp().exp()
        override fun ln(): ComplexNumber = asExp().ln()

        override fun sin(): ComplexNumber = asExp().sin()
        override fun cos(): ComplexNumber = asExp().cos()

        override fun toString() = "$modulus * exp($argument)"
    }

    object Math : HyperOperation.WithOther.Math<ComplexNumber, RealNumber>,
            TrigonometricFunctions.Math<ComplexNumber>,
            LogAndExp.WithOther.Math<ComplexNumber, RealNumber> {
        override val ZERO: ComplexNumber
            get() = ComplexNumber.ZERO
    }
}