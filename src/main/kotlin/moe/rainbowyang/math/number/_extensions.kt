package moe.rainbowyang.math.number

import moe.rainbowyang.math.operation.cos
import moe.rainbowyang.math.operation.sin
import kotlin.math.acos

/**
 * Number包扩展
 * @author: Rainbow Yang
 * @create: 2018-11-14 17:29
 **/

val CLASSES_OF_NUMBERS = mapOf(
        Real::class.java to Real::class.java.getConstructor(Number::class.java)
        , Complex::class.java to Complex::class.java.getConstructor(Number::class.java)
        , Quaternion::class.java to Quaternion::class.java.getConstructor(Number::class.java)
)

inline fun <reified T : AbstractNumber<T>> Number.toThis(): T =
        CLASSES_OF_NUMBERS[T::class.java]?.newInstance(this) as T

inline operator fun <reified T : AbstractNumber<T>> Number.plus(other: T) = this.toThis<T>() + other
inline operator fun <reified T : AbstractNumber<T>> T.plus(other: Number) = this + other.toThis()

inline operator fun <reified T : AbstractNumber<T>> Number.minus(other: T) = this.toThis<T>() - other
inline operator fun <reified T : AbstractNumber<T>> T.minus(other: Number) = this - other.toThis()

inline operator fun <reified T : AbstractNumber<T>> Number.times(other: T) = this.toThis<T>() * other
inline operator fun <reified T : AbstractNumber<T>> T.times(other: Number) = this * other.toThis()

inline operator fun <reified T : AbstractNumber<T>> Number.div(other: T) = this.toThis<T>() / other
inline operator fun <reified T : AbstractNumber<T>> T.div(other: Number) = this / other.toThis()

inline fun <reified T : AbstractNumber<T>> Number.power(other: T) = this.toThis<T>() power other
inline fun <reified T : AbstractNumber<T>> T.power(other: Number) = this power other.toThis()

inline fun <reified T : AbstractNumber<T>> Number.log(other: T) = this.toThis<T>() log other
inline fun <reified T : AbstractNumber<T>> T.log(other: Number) = this log other.toThis()

fun Number.toReal() = Real(this)
fun Number.toComplex() = Complex(this)
fun Number.toQuaternion() = Quaternion(this)

fun Real.toComplex() = this withI Real.ZERO
fun Real.toQuaternion() = Quaternion(this, Real.ZERO, Real.ZERO, Real.ZERO)

fun Complex.toQuaternion() = Quaternion(real, imag, Real.ZERO, Real.ZERO)


infix fun Real.withI(imag: Real) = Complex(this, imag)
infix fun Real.expI(argument: Real) = ComplexAsExp(this, argument)
fun ComplexAsExp(modulus: Real, argument: Real) =
        Complex(modulus * cos(argument), modulus * sin(argument))

fun inner(p: Quaternion, q: Quaternion) = q.a * p.a + q.b * p.b + q.c * p.c + q.d * p.d
fun outer(p: Quaternion, q: Quaternion) = (p.conjugate() * q - q.conjugate() * p) / 2.toQuaternion()
fun even(p: Quaternion, q: Quaternion) = (p * q + q * p) / 2.toQuaternion()
fun odd(p: Quaternion, q: Quaternion) = (p * q - q * p) / 2.toQuaternion()
fun sgn(p: Quaternion) = p / p.modulus.toQuaternion()
fun arg(p: Quaternion) = acos((p.a / p.modulus).value)

fun sqrt(num: Real) = Real(kotlin.math.sqrt(num.value))
fun atan2(y: Real, x: Real) = Real(kotlin.math.atan2(y.value, x.value))
fun lengthOf(vararg nums: Real) = nums.asList().lengthOf()
fun List<Real>.lengthOf() = sqrt(sumOfSquare())
fun sumOfSquare(vararg nums: Real) = nums.asList().sumOfSquare()
fun List<Real>.sumOfSquare() = fold(Real.ZERO) { sum, next -> sum + next * next }
fun List<Real>.checkValues() = all { !it.value.isNaN() || !it.value.isInfinite() }

