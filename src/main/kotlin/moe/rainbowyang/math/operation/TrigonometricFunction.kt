package moe.rainbowyang.math.operation

/**
 * 抽象三角函数运算
 * 依赖[Multiplication]
 * @author Rainbow Yang
 */
interface TrigonometricFunction<T : Multiplication<T>> : Operation<T> {
    
    fun sin(): T
    fun cos(): T
    fun tan(): T = sin() / cos()
    fun cot(): T = cos() / sin()
    fun sec(): T = cos().reciprocal()
    fun csc(): T = sin().reciprocal()
    
}
