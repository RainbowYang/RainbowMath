package moe.rainbowyang.math

import kotlin.math.*
import kotlin.math.ln as ktLn

// 进行对ComplexNumber的运算扩展

/** 便于外部方便地进行withI的调用 */
infix fun Number.withI(i: Number) = with(ComplexNumber) { this@withI withI i }

fun Number.asComplexNumber() = this.toDouble() withI 0

operator fun Number.plus(number: ComplexNumber) = number + this
operator fun Number.minus(number: ComplexNumber) = -number + this
operator fun Number.times(number: ComplexNumber) = number * this
operator fun Number.div(number: ComplexNumber) = number.reciprocal() * this

//对数

fun ln(number: Number) = ln(number.asComplexNumber())
fun ln(number: ComplexNumber) = ktLn(number.modulus) withI number.argument

fun log(base: ComplexNumber, number: ComplexNumber) = ln(number) / ln(base)
fun log(base: Number, number: ComplexNumber) = ln(number) / ln(base)
fun log(base: ComplexNumber, number: Number) = ln(number) / ln(base)


//指数

fun cis(number: Number) = cos(number.toDouble()) withI sin(number.toDouble())
fun exp(number: ComplexNumber) = exp(number.real) * cis(number.imag)

fun power(base: ComplexNumber, number: ComplexNumber) = exp(ln(base) * number)
fun power(base: Number, number: ComplexNumber) = exp(ln(base) * number)
fun power(base: ComplexNumber, number: Number) = exp(ln(base) * number)


//三角函数

fun sin(number: ComplexNumber) = with(number) {
    sin(real) * cosi(imag) + sini(imag) * cos(real)
}

fun cos(number: ComplexNumber) = with(number) {
    cos(real) * cosi(imag) - sini(imag) * sin(real)
}

fun tan(number: ComplexNumber) = sin(number) / cos(number)

// means sin( number i )
private fun sini(number: Double) = 0 withI (exp(number) - exp(-number)) / 2

// means cos( number i )
private fun cosi(number: Double) = (exp(number) + exp(-number)) / 2

