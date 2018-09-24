package moe.rainbowyang.math.operation

/**
 * 抽象运算
 * @author Rainbow Yang
 */
interface Operation<This> {
    fun Number.asThis(): This

    interface WithOther<This, Other> : Operation<This> {
        fun Other.asThis(): This
    }

    /**
     * 此接口用于扩展
     * 须由单例实现，提供静态方法
     */
    interface Math<This> {
        val ZERO: This
    }
}