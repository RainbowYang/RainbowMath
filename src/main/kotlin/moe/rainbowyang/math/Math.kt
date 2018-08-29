package moe.rainbowyang.math

/**
 * @author Rainbow Yang
 */

fun lengthOf(vararg nums: Number) = Math.sqrt(sumOfSquare(*nums))

fun sumOfSquare(vararg nums: Number) = nums.fold(0.0) { sum, it -> sum + it.toDouble() * it.toDouble() }
