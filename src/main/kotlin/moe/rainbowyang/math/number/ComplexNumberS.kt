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
import moe.rainbowyang.math.number.ComplexNumberS.Normal.Companion.withI
import moe.rainbowyang.math.number.ComplexNumberS.Math.plus
import moe.rainbowyang.math.number.ComplexNumberS.Math.minus
import moe.rainbowyang.math.number.ComplexNumberS.Math.times

/**
 * 复数
 * @author Rainbow Yang
 */
abstract class ComplexNumberS :
        Number<ComplexNumberS>,
        HyperOperation.WithOther<ComplexNumberS, RealNumber>,
        TrigonometricFunctions<ComplexNumberS>,
        LogAndExp.WithOther<ComplexNumberS, RealNumber> {

    companion object {
        val ZERO: ComplexNumberS = 0 withI 0
    }

    open val real: RealNumber get() = asNormal().real
    open val imag: RealNumber get() = asNormal().imag
    open val modulus: RealNumber get() = asExp().modulus
    open val argument: RealNumber get() = asExp().argument

    abstract fun asNormal(): Normal
    abstract fun asExp(): AsExp

    abstract fun conjugate(): ComplexNumberS

    protected fun sini(num: RealNumber) = 0 withI (exp(num) - exp(-num)) / 2
    protected fun cosi(num: RealNumber) = (exp(num) + exp(-num)) / 2

    /**
     * 复数表示为a+bi
     */
    class Normal(override val real: RealNumber, override val imag: RealNumber) : ComplexNumberS() {
        companion object {
            infix fun RealNumber.withI(imag: RealNumber) = Normal(this, imag)
            infix fun kotlin.Number.withI(imag: kotlin.Number) = this.asThis() withI RealNumber(imag)
            infix fun kotlin.Number.withI(imag: RealNumber) = this.asThis() withI imag
            infix fun RealNumber.withI(imag: kotlin.Number) = this withI imag.asThis()
        }

        override fun asNormal() = this
        override fun asExp() = AsExp(lengthOf(real, imag), atan2(real, imag))

        override fun kotlin.Number.asThis(): ComplexNumberS = this withI 0
        override fun RealNumber.asThis(): ComplexNumberS = this withI 0

        override fun plus(other: ComplexNumberS) = real + other.real withI imag + other.imag
        override fun times(other: ComplexNumberS) = asExp() * other

        override fun unaryMinus() = -real withI -imag
        override fun reciprocal() = asExp().reciprocal()

        override fun conjugate(): ComplexNumberS = real withI -imag

        override fun exp(): ComplexNumberS = exp(real) * cis(imag)
        override fun ln(): ComplexNumberS = ln(modulus) withI argument

        override fun sin(): ComplexNumberS = sin(real) * cosi(imag) + sini(imag) * cos(real)
        override fun cos(): ComplexNumberS = cos(real) * cosi(imag) - sini(imag) * sin(real)

        private fun cis(num: RealNumber) = cos(num) withI sin(num)

        override fun toString() = "$real ${if (imag >= RealNumber.ZERO) "+" else ""} ${imag}i"
    }

    class AsExp(override val modulus: RealNumber, override val argument: RealNumber) : ComplexNumberS() {
        companion object {
            infix fun RealNumber.expI(argument: RealNumber) = AsExp(this, argument)
            infix fun kotlin.Number.expI(argument: kotlin.Number) = asThis() expI argument.asThis()
            infix fun kotlin.Number.expI(argument: RealNumber) = asThis() expI argument
            infix fun RealNumber.expI(argument: kotlin.Number) = this expI argument.asThis()
        }

        override fun asNormal() = Normal(modulus * cos(argument), modulus * sin(argument))
        override fun asExp() = this

        override fun kotlin.Number.asThis(): ComplexNumberS = this expI 0
        override fun RealNumber.asThis(): ComplexNumberS = this expI 0

        override fun plus(other: ComplexNumberS) = this.asNormal() + other
        override fun times(other: ComplexNumberS) = modulus * other.modulus expI argument + other.argument

        override fun unaryMinus() = asNormal().unaryMinus()
        override fun reciprocal() = modulus.reciprocal() expI -argument

        override fun conjugate(): ComplexNumberS = modulus expI -argument

        override fun exp(): ComplexNumberS = asExp().exp()
        override fun ln(): ComplexNumberS = asExp().ln()

        override fun sin(): ComplexNumberS = asExp().sin()
        override fun cos(): ComplexNumberS = asExp().cos()

        override fun toString() = "$modulus * exp($argument)"
    }

    object Math : HyperOperation.WithOther.Math<ComplexNumberS, RealNumber>,
            TrigonometricFunctions.Math<ComplexNumberS>,
            LogAndExp.WithOther.Math<ComplexNumberS, RealNumber> {
        override val ZERO: ComplexNumberS
            get() = ComplexNumberS.ZERO
    }
}