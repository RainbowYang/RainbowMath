package moe.rainbowyang.math.number

import moe.rainbowyang.math.number.Complex.Normal.Companion.withI
import kotlin.math.acos

/**
 * Number包扩展
 * @author: Rainbow Yang
 * @create: 2018-11-14 17:29
 **/

fun Number.asReal() = Real(this)
fun Number.asComplex() = this.asReal() withI Real.ZERO
fun Number.asQuaternion() = Quaternion(this.asReal(), Real.ZERO, Real.ZERO, Real.ZERO)

fun Real.asComplex() = this withI Real.ZERO
fun Real.asQuaternion() = Quaternion(this, Real.ZERO, Real.ZERO, Real.ZERO)

fun Complex.asQuaternion() = Quaternion(real, imag, Real.ZERO, Real.ZERO)

fun inner(p: Quaternion, q: Quaternion) = q.a * p.a + q.b * p.b + q.c * p.c + q.d * p.d
fun outer(p: Quaternion, q: Quaternion) = (p.conjugate() * q - q.conjugate() * p) / 2.asQuaternion()
fun even(p: Quaternion, q: Quaternion) = (p * q + q * p) / 2.asQuaternion()
fun odd(p: Quaternion, q: Quaternion) = (p * q - q * p) / 2.asQuaternion()
fun sgn(p: Quaternion) = p / p.modulus.asQuaternion()
fun arg(p: Quaternion) = acos((p.a / p.modulus).value)

fun sqrt(num: Real) = Real(kotlin.math.sqrt(num.value))
fun atan2(y: Real, x: Real) = Real(kotlin.math.atan2(y.value, x.value))
fun lengthOf(vararg nums: Real) = nums.asList().lengthOf()
fun List<Real>.lengthOf() = sqrt(sumOfSquare())
fun sumOfSquare(vararg nums: Real) = nums.asList().sumOfSquare()
fun List<Real>.sumOfSquare() =
        fold(Real.ZERO) { sum, next -> sum + next * next }
