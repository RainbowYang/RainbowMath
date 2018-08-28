package moe.rainbowyang.math

/**
 * 复数
 * @author Rainbow Yang
 */
data class ComplexNumber(val real: Double, val imaginary: Double) {
    constructor(real: Number, imaginary: Number) : this(real.toDouble(), imaginary.toDouble())

    /**复数表示为a+bi*/
    val a = real
    val b = imaginary

    val length: Double = Math.sqrt(a * a + b * b)
    val theta: Double = Math.atan2(a, b)

    fun isReal() = imaginary == 0.0
    fun asReal() = real

    operator fun plus(other: ComplexNumber) =
            ComplexNumber(this.real + other.real, this.imaginary + other.imaginary)

    operator fun minus(other: ComplexNumber) = this + (-other)
    operator fun unaryMinus() = ComplexNumber(-real, -imaginary)

    operator fun plus(other: Number) = copy(real = real + other.toDouble())
    operator fun minus(other: Number) = copy(real = real - other.toDouble())

    operator fun times(other: ComplexNumber): ComplexNumber {
        val (a1, b1) = this; val (a2, b2) = other
        return ComplexNumber(a1 * a2 - b1 * b2, a1 * b2 + a2 * b1)
    }

    operator fun div(other: ComplexNumber) = this * other.reciprocal()

    operator fun times(other: Number) =
            ComplexNumber(real * other.toDouble(), imaginary * other.toDouble())

    operator fun div(other: Number) =
            ComplexNumber(real / other.toDouble(), imaginary / other.toDouble())

    /**倒数*/
    fun reciprocal() = this.conjugate() / (a * a + b * b)

    /**共轭*/
    fun conjugate() = ComplexNumber(real, -imaginary)

    override fun toString(): String {
        return "ComplexNumber($real+${imaginary}i)"
    }


}