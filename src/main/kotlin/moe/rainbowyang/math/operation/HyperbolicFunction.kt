package moe.rainbowyang.math.operation

/**
 * 抽象双曲三角函数运算
 * 依赖[Multiplication]
 * @author Rainbow Yang
 */
interface HyperbolicFunction<T : Multiplication<T>> : Operation<T> {
    
    fun sinh(): T
    fun cosh(): T
    fun tanh(): T = sinh() / cosh()
    fun coth(): T = cosh() / sinh()
    fun sech(): T = cosh().reciprocal()
    fun csch(): T = sinh().reciprocal()
    
}

