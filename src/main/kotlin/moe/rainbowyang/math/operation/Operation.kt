package moe.rainbowyang.math.operation

/**
 * 抽象运算
 * @author Rainbow Yang
 */
interface Operation<This> {
    fun Number.asThis(): This

    /**
     * 此接口用于扩展
     * 须由单例实现，提供静态方法
     */
    interface Math<This : Operation<This>> {
        val ZERO: This
        fun Number.asThis(): This = with(ZERO) { asThis() }
    }

    interface WithOther<This, Other> : Operation<This> {
        fun Other.asThis(): This

        interface Math<This, Other> : Operation.Math<This>
                where This : WithOther<This, Other>, This : Operation<This> {
            fun Other.asThis(): This = with(ZERO) { asThis() }
        }
    }
}