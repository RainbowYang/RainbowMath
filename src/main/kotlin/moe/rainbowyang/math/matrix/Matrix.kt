package moe.rainbowyang.math.matrix

import moe.rainbowyang.math.*
import moe.rainbowyang.math.operation.HyperOperation

/**
 * 本类表示矩阵
 *
 * 不可变，所有运算均会创建新的类
 *
 * @author Rainbow Yang
 */
class Matrix(val values: List<List<Number>>) : HyperOperation<Matrix> {
    constructor(vararg inits: List<Number>) : this(inits.asList())
    constructor(row: Int, column: Int, maker: (Int, Int) -> Number) :
            this(List(row) { r -> List(column) { c -> maker(r, c) } })

    private fun Matrix(maker: (Int, Int) -> Number) = Matrix(row, column, maker)

    val row = values.size
    val column = values.first().size

    operator fun get(index: Int) = values[index]

    /** 转置 */
    fun transposition() = Matrix(column, row) { r, c -> this[c][r] }

    override operator fun plus(other: Matrix): Matrix {
        check(row == other.row && column == other.column) { "plus is not allowed here" }
        return Matrix { r, c -> this[r][c] + other[r][c] }
    }

    override fun unaryMinus() = Matrix { r, c -> -this[r][c] }
    override fun Number.asThis(): Matrix = Matrix { _, _ -> this }

    override operator fun times(times: Number) = Matrix { r, c -> this[r][c] * times }
    override operator fun div(div: Number) = Matrix { r, c -> this[r][c] / div }

    override operator fun times(other: Matrix): Matrix {
        check(column == other.row) { "times is not allowed here" }
        return Matrix(row, other.column) { r, c ->
            List(column) { this[r][it] * other[it][c] }.sumByDouble { it }
        }
    }

    override fun reciprocal(): Matrix {
        check(row == column) { "reciprocal is not allowed here" }
        TODO("not implemented")
    }

    override fun toString() = StringBuilder().apply {
        values.forEach { it.forEach { append("$it\t") }; append("\n") }
    }.toString()
}
