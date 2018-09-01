package moe.rainbowyang.math.number

import kotlin.math.*
import kotlin.math.ln as ktLn

// 进行对ComplexNumber的运算扩展

/** 便于外部方便地进行withI的调用 */
infix fun Number.withI(i: Number) = with(ComplexNumber) { this@withI withI i }

fun Number.asComplexNumber() = this.toDouble() withI 0

operator fun Number.plus(cNum: ComplexNumber) = cNum + this
operator fun Number.minus(cNum: ComplexNumber) = -cNum + this
operator fun Number.times(cNum: ComplexNumber) = cNum * this
operator fun Number.div(cNum: ComplexNumber) = cNum.reciprocal() * this

//对数

fun ln(num: Number) = ln(num.asComplexNumber())
fun ln(cNum: ComplexNumber) = ktLn(cNum.modulus) withI cNum.argument

fun log(base: ComplexNumber, cNum: ComplexNumber) = ln(cNum) / ln(base)
fun log(base: Number, cNum: ComplexNumber) = ln(cNum) / ln(base)
fun log(base: ComplexNumber, num: Number) = ln(num) / ln(base)


//指数

fun cis(num: Number) = cos(num.toDouble()) withI sin(num.toDouble())
fun exp(cNum: ComplexNumber) = exp(cNum.real) * cis(cNum.imag)

fun pow(base: ComplexNumber, cNum: ComplexNumber) = exp(ln(base) * cNum)
fun pow(base: Number, cNum: ComplexNumber) = exp(ln(base) * cNum)
fun pow(base: ComplexNumber, num: Number) = exp(ln(base) * num)


//三角函数

fun sin(cNum: ComplexNumber) = with(cNum) { sin(real) * cosi(imag) + sini(imag) * cos(real) }
fun cos(cNum: ComplexNumber) = with(cNum) { cos(real) * cosi(imag) - sini(imag) * sin(real) }
fun tan(cNum: ComplexNumber) = sin(cNum) / cos(cNum)

private fun sini(num: Double) = 0 withI (exp(num) - exp(-num)) / 2
private fun cosi(num: Double) = (exp(num) + exp(-num)) / 2

