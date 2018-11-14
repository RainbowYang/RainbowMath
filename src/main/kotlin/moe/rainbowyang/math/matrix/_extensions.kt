package moe.rainbowyang.math.matrix

import moe.rainbowyang.math.number.asReal

/**
 * Matrix扩展
 * @author: Rainbow Yang
 * @create: 2018-11-14 17:28
 **/

fun Matrix(values: List<List<Number>>) = Matrix(values.map { it.map { it.asReal() } })
fun Matrix(vararg values: List<Number>) = Matrix(values.asList())
