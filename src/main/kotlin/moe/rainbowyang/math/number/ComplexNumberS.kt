package moe.rainbowyang.math.number

import moe.rainbowyang.math.number.RealNumbeMath.*

/**
 * 复数
 * @author Rainbow Yang
 */
abstract class ComplexNumberS : Number<ComplexNumberS> {

    open val real: RealNumber get() = asNormal().real
    open val imag: RealNumber get() = asNormal().imag
    open val modulus: RealNumber get() = asExp().modulus
    open val argument: RealNumber get() = asExp().argument

    abstract fun asNormal(): Normal
    abstract fun asExp(): AsExp

    /**
     * 复数表示为a+bi
     */
    class Normal(override val real: RealNumber, override val imag: RealNumber) : ComplexNumberS() {
        companion object {
            infix fun RealNumber.withI(imag: RealNumber): ComplexNumberS = Normal(this, imag)
        }

        override fun asNormal() = this
        override fun asExp() = AsExp(lengthOf(real, imag), atan2(real, imag))

        override fun plus(other: ComplexNumberS) = real + other.real withI imag + other.imag
        override fun times(other: ComplexNumberS) = asExp() * other

        override fun unaryMinus() = -real withI -imag
        override fun reciprocal() = asExp().reciprocal()

        fun conjugate(): ComplexNumberS = real withI -imag

        override fun toString() = "$real ${if (imag >= ZERO) "+" else ""} ${imag}i"
    }

    class AsExp(override val modulus: RealNumber, override val argument: RealNumber) : ComplexNumberS() {
        companion object {
            infix fun RealNumber.expI(argument: RealNumber): ComplexNumberS = AsExp(this, argument)
        }

        override fun asNormal() = Normal(modulus * cos(argument), modulus * sin(argument))
        override fun asExp() = this

        override fun plus(other: ComplexNumberS) = this.asNormal() + other
        override fun times(other: ComplexNumberS) =
                modulus * other.modulus expI argument + other.argument

        override fun unaryMinus() = asNormal().unaryMinus()
        override fun reciprocal() = modulus.reciprocal() expI -argument

        override fun toString() = "$modulus * exp($argument)"
    }
}