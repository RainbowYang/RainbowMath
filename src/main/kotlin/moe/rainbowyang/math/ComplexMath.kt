package moe.rainbowyang.math

import java.lang.Math.*

//复数计算库

operator fun Number.plus(number: ComplexNumber) = number + this
operator fun Number.minus(number: ComplexNumber) = -(number - this)
operator fun Number.times(number: ComplexNumber) = number * this
operator fun Number.div(number: ComplexNumber) = number.reciprocal() * this

fun log(number: ComplexNumber) = ComplexNumber(log(number.length), number.theta)
fun log(base: ComplexNumber, number: ComplexNumber) = log(number) / log(base)
fun log(base: Number, number: ComplexNumber) = log(number) / log(base.toDouble())
fun log(base: ComplexNumber, number: Number) = log(number.toDouble()) / log(base)

private fun expi(number: Number) = ComplexNumber(cos(number.toDouble()), sin(number.toDouble()))
fun exp(number: ComplexNumber) = expi(number.imaginary) * exp(number.real)
fun power(base: ComplexNumber, number: ComplexNumber) = exp(log(base) * number)
fun power(base: Number, number: ComplexNumber) = exp(log(base.toDouble()) * number)
fun power(base: ComplexNumber, number: Number) = exp(log(base) * number)

private fun sini(number: Double) = ComplexNumber(0, (exp(number) - exp(-number)) / 2)
private fun cosi(number: Double) = (exp(number) + exp(-number)) / 2

fun tan(number: ComplexNumber) = sin(number) / cos(number)

fun sin(number: ComplexNumber): ComplexNumber {
    val (a, b) = number
    return sin(a) * cosi(b) + sini(b) * cos(a)
}

fun cos(number: ComplexNumber): ComplexNumber {
    val (a, b) = number
    return cos(a) * cosi(b) - sini(b) * sin(a)
}

fun main(args: Array<String>) {
    println(power(ComplexNumber(-1, 0), 1.0 / 2))
}
