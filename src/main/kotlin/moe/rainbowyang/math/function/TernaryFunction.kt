package moe.rainbowyang.math.function

import moe.rainbowyang.math.operation.Addition
import moe.rainbowyang.math.operation.Multiplication

/**
 * 三输入单输出函数
 * @author: Rainbow Yang
 * @create: 2018-11-13 23:18
 **/
class TernaryFunction<T>(val function: (T, T, T) -> T) :
        Function, Addition<TernaryFunction<T>>, Multiplication<TernaryFunction<T>>
        where T : Addition<T>, T : Multiplication<T> {

    operator fun invoke(input1: T, input2: T, input3: T): T = function(input1, input2, input3)

    override fun plus(other: TernaryFunction<T>): TernaryFunction<T> =
            TernaryFunction { i1, i2, i3 -> this(i1, i2, i3) + other(i1, i2, i3) }

    override fun unaryMinus(): TernaryFunction<T> =
            TernaryFunction { i1, i2, i3 -> -this(i1, i2, i3) }

    override fun times(other: TernaryFunction<T>): TernaryFunction<T> =
            TernaryFunction { i1, i2, i3 -> this(i1, i2, i3) * other(i1, i2, i3) }

    override fun reciprocal(): TernaryFunction<T> =
            TernaryFunction { i1, i2, i3 -> this(i1, i2, i3).reciprocal() }

}
