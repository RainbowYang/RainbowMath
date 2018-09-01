package moe.rainbowyang.math

import kotlin.math.*

fun lengthOf(vararg nums: Number) = sqrt(sumOfSquare(*nums))
fun <N : Number> List<N>.lengthOf() = sqrt(sumOfSquare())
fun sumOfSquare(vararg nums: Number) = nums.sumByDouble { it.toDouble() * it.toDouble() }
fun <N : Number> List<N>.sumOfSquare() = sumByDouble { it.toDouble() * it.toDouble() }
fun checkValues(vararg values: Number) = values.all { !it.toDouble().isNaN() || !it.toDouble().isInfinite() }
fun <N : Number> List<N>.checkValues() = all { !it.toDouble().isNaN() || !it.toDouble().isInfinite() }

fun until(end: Int) = 0 until end

infix fun Number.almostEquals(other: Number) = this.almostEquals(other, 10)
fun Number.almostEquals(other: Number, accuracy: Int) =
        abs(this.toDouble() - other.toDouble()) < (0.1.pow(accuracy.toDouble()))
