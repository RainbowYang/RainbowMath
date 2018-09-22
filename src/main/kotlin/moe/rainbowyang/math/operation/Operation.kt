package moe.rainbowyang.math.operation

/**
 * 抽象运算
 * @author Rainbow Yang
 */
interface Operation<This> {
    fun Number.asThis(): This
}

//interface OperationForOther<This, Other> : Operation<This> {
//    fun Other.asThis(): This
//}
