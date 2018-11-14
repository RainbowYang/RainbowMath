package moe.rainbowyang.math.function

import moe.rainbowyang.math.operation.Addition
import moe.rainbowyang.math.operation.Multiplication

/**
 * 多输入单输出函数
 * @author: Rainbow Yang
 * @create: 2018-11-13 23:29
 **/
class MultiFunction<T>(val size: Int, val function: (List<T>) -> T) :
        Function, Addition<MultiFunction<T>>, Multiplication<MultiFunction<T>>
        where T : Addition<T>, T : Multiplication<T> {
    
    operator fun invoke(vararg inputs: T): T = function(inputs.asList())
    operator fun invoke(inputs: List<T>): T = function(inputs)
    
    override fun plus(other: MultiFunction<T>): MultiFunction<T> = MultiFunction(size) { this(it) + other(it) }
    override fun unaryMinus(): MultiFunction<T> = MultiFunction(size) { -this(it) }
    override fun times(other: MultiFunction<T>): MultiFunction<T> = MultiFunction(size) { this(it) * other(it) }
    override fun reciprocal(): MultiFunction<T> = MultiFunction(size) { this(it).reciprocal() }
    
}
