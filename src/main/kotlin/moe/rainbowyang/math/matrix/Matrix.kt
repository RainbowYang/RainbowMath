package moe.rainbowyang.math.matrix

import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.number.toReal
import moe.rainbowyang.math.operation.Addition
import moe.rainbowyang.math.operation.Multiplication

/**
 * 本类表示矩阵
 * 不可变，所有运算均会创建新的类
 * @author Rainbow Yang
 */
class Matrix(val values: List<List<Real>>) : Addition<Matrix>, Multiplication<Matrix> {
    
    constructor(vararg values: List<Real>) : this(values.asList())
    constructor(row: Int, column: Int, maker: (Int, Int) -> Real) :
            this(List(row) { r -> List(column) { c -> maker(r, c) } })
    
    val row = values.size
    val column = values.first().size
    
    operator fun get(index: Int) = values[index]
    
    /** 转置 */
    fun transposition() = Matrix(column, row) { r, c -> this[c][r] }
    
    override operator fun plus(other: Matrix): Matrix {
        check(row == other.row && column == other.column) { "plus is not allowed here" }
        return createMatrixWithTheSameSize { r, c -> this[r][c] + other[r][c] }
    }
    
    override fun unaryMinus() = createMatrixWithTheSameSize { r, c -> -this[r][c] }
    
    operator fun times(times: Real) = createMatrixWithTheSameSize { r, c -> this[r][c] * times }
    operator fun div(div: Real) = createMatrixWithTheSameSize { r, c -> this[r][c] / div }
    
    override operator fun times(other: Matrix): Matrix {
        check(column == other.row) { "times is not allowed here" }
        return Matrix(row, other.column) { r, c ->
            List(column) { this[r][it].value * other[it][c].value }.sum().toReal()
        }
    }
    
    override fun div(other: Matrix): Matrix {
        throw IllegalArgumentException("div is not allowed between Matrix")
    }
    
    override fun reciprocal(): Matrix {
        check(row == column) { "reciprocal is not allowed here" }
        TODO("not implemented")
    }
    
    fun createMatrixWithTheSameSize(maker: (Int, Int) -> Real) = Matrix(row, column, maker)
    
    override fun toString() = StringBuilder().apply {
        values.forEach { it.forEach { append("$it\t") }; append("\n") }
    }.toString()
    
}
