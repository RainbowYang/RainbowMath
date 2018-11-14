package moe.rainbowyang.math.function

import moe.rainbowyang.math.operation.Addition
import moe.rainbowyang.math.operation.Multiplication

/**
 * 单输入单输出函数
 * @author: Rainbow Yang
 * @create: 2018-11-13 17:55
 **/
class UnaryFunction<T>(val function: (T) -> T) :
        Function, Addition<UnaryFunction<T>>, Multiplication<UnaryFunction<T>>
        where T : Addition<T>, T : Multiplication<T> {
    
    //复合函数
    operator fun invoke(inner: UnaryFunction<T>): UnaryFunction<T> = UnaryFunction { this(inner(it)) }
    operator fun invoke(input: T): T = function(input)
    
    override fun plus(other: UnaryFunction<T>): UnaryFunction<T> = UnaryFunction { this(it) + other(it) }
    override fun unaryMinus(): UnaryFunction<T> = UnaryFunction { -this(it) }
    override fun times(other: UnaryFunction<T>): UnaryFunction<T> = UnaryFunction { this(it) * other(it) }
    override fun reciprocal(): UnaryFunction<T> = UnaryFunction { this(it).reciprocal() }
    
}
