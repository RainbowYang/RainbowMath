package moe.rainbowyang.math.number

import moe.rainbowyang.math.operation.*
import moe.rainbowyang.math.toBeImplemented

/**
 * 抽象数字
 * @author Rainbow Yang
 */
interface AbstractNumber<T> :
        Addition<T>, Multiplication<T>, Exponentiation<T>, TrigonometricFunction<T>, HyperbolicFunction<T>
        where T : Addition<T>, T : Multiplication<T>, T : Exponentiation<T> {
    
    override fun plus(other: T): T = toBeImplemented()
    override fun unaryMinus(): T = toBeImplemented()
    override fun times(other: T): T = toBeImplemented()
    override fun reciprocal(): T = toBeImplemented()
    override fun exp(): T = toBeImplemented()
    override fun ln(): T = toBeImplemented()
    
    override fun sin(): T = toBeImplemented()
    override fun cos(): T = toBeImplemented()
    override fun sinh(): T = toBeImplemented()
    override fun cosh(): T = toBeImplemented()
    
}
