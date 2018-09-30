package moe.rainbowyang.math.operation

/**
 * 超运算
 * @author Rainbow Yang
 */
interface HyperOperation<This> : HyperOperation1<This>, HyperOperation2<This>

interface HyperOperation1<This> : Operation<This> {
    operator fun plus(other: This): This
    operator fun minus(other: This): This = plus(unaryMinus())
    operator fun unaryMinus(): This
}

interface HyperOperation2<This> : Operation<This> {
    operator fun times(other: This): This
    operator fun div(other: This): This = times(reciprocal())
    /*hold*/ fun reciprocal(): This
}
