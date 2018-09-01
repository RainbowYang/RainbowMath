package moe.rainbowyang.math.number

import moe.rainbowyang.math.lengthOf

/**
 * 本类表示复数类，并给出相关的计算方式
 *
 * 原有的[Number]将被视为实数，所以此类不继承[Number]
 *
 * @author Rainbow Yang
 */

data class ComplexNumber(val real: Double, val imag: Double) {
    constructor(real: Number, imaginary: Number) : this(real.toDouble(), imaginary.toDouble())

    companion object {
        infix fun Number.withI(i: Number) = ComplexNumber(this, i)
    }

    /** 模长 */
    val modulus = lengthOf(real, imag)
    /** 辐角 */
    val argument = Math.atan2(real, imag)

    /** 倒数 */
    fun reciprocal() = this.conjugate() / (real * real + imag * imag)

    /** 共轭 */
    fun conjugate() = real withI -imag

    operator fun plus(other: Number) = (real + other.toDouble()) withI imag
    operator fun plus(other: ComplexNumber) = (real + other.real) withI (imag + other.imag)

    operator fun minus(other: Number) = this + (-other.toDouble())
    operator fun minus(other: ComplexNumber) = this + (-other)
    operator fun unaryMinus() = -real withI -imag

    operator fun times(other: Number) = real * other.toDouble() withI imag * other.toDouble()
    operator fun times(other: ComplexNumber): ComplexNumber {
        val (a1, b1) = this
        val (a2, b2) = other
        return (a1 * a2 - b1 * b2) withI (a1 * b2 + a2 * b1)
    }

    operator fun div(other: Number) = this * (1 / other.toDouble())
    operator fun div(other: ComplexNumber) = this * other.reciprocal()

    override fun toString() = "$real+${imag}i"
}