package moe.rainbowyang.math.function

import moe.rainbowyang.math.operation.Addition
import moe.rainbowyang.math.operation.Multiplication

/**
 * 双输入单输出函数
 * @author: Rainbow Yang
 * @create: 2018-11-13 18:18
 **/
class BinaryFunction<T>(val function: (T, T) -> T) :
        Function, Addition<BinaryFunction<T>>, Multiplication<BinaryFunction<T>>
        where T : Addition<T>, T : Multiplication<T> {
    
    operator fun invoke(input1: T, input2: T): T = function(input1, input2)
    
    override fun plus(other: BinaryFunction<T>): BinaryFunction<T> =
            BinaryFunction { i1, i2 -> this(i1, i2) + other(i1, i2) }
    
    override fun unaryMinus(): BinaryFunction<T> =
            BinaryFunction { i1, i2 -> -this(i1, i2) }
    
    override fun times(other: BinaryFunction<T>): BinaryFunction<T> =
            BinaryFunction { i1, i2 -> this(i1, i2) * other(i1, i2) }
    
    override fun reciprocal(): BinaryFunction<T> =
            BinaryFunction { i1, i2 -> this(i1, i2).reciprocal() }
    
}
