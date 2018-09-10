package moe.rainbowyang.math.matrix

import moe.rainbowyang.math.plus
import moe.rainbowyang.math.times
import java.util.*

/**
 * 本类表示矩阵
 *
 * 不可变，所有运算均会创建新的类
 *
 * @author Rainbow Yang
 */
class Matrix(val values: List<List<Number>>) {
    constructor(vararg inits: List<Number>) : this(inits.asList())

    val row = values.size
    val column = values.first().size

    operator fun get(index: Int) = values[index]

    /** 转置 */
    fun transposition() = Matrix(List(column) { List(row) { i -> this[i][it] } })

    operator fun plus(other: Matrix) =
            if (hasSameSizeWith(other))
                Matrix(List(row) { this[it] + other[it] })
            else
                throw IllegalArgumentException("Plus function is not allowed between the two matrices that have different size.")

    operator fun times(times: Number) = Matrix(List(row) { this[it] * times })

    operator fun times(other: Matrix) =
            if (column==other.row)
                Matrix(List(row) { i ->
                    List(other.column) { j ->
                        List(column) { this[i][it] * other[it][j] }.sumByDouble { it }
                    }
                })
            else
                throw IllegalArgumentException("Times function is not allowed between the two matrices that have worry size.")


    private infix fun hasSameSizeWith(other: Matrix)=row == other.row && column == other.column

    private operator fun List<Number>.plus(other: List<Number>) =
            List(size) { this[it] + other[it] }

    private operator fun List<Number>.times(times: Number) =
            List(size) { this[it] * times }

    override fun toString() = StringBuilder().apply {
        values.forEach { it.forEach { append("$it\t") };append("\n") }
    }.toString()

}
