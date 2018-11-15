package moe.rainbowyang.math.matrix

import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.number.toReal

/**
 * Matrix扩展
 * @author: Rainbow Yang
 * @create: 2018-11-14 17:28
 **/

fun Matrix(values: List<List<Number>>) = Matrix(values.map { it.map { it.toReal() } })
fun Matrix(vararg values: List<Number>) = Matrix(values.asList())

operator fun Matrix.times(times: Number) = times(times.toReal())
operator fun Matrix.div(div: Number) = div(div.toReal())
operator fun Number.times(matrix: Matrix) = matrix * this
operator fun Real.times(matrix: Matrix) = matrix * this
