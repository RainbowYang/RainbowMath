package moe.rainbowyang.math.operation

/**
 * 超运算
 * @author Rainbow Yang
 */
interface HyperOperation<This> : HyperOperation1<This>, HyperOperation2<This> {
    interface Math<This : HyperOperation<This>> : HyperOperation1.Math<This>, HyperOperation2.Math<This>
    interface WithOther<This : HyperOperation.WithOther<This, Other>, Other> :
            HyperOperation1.WithOther<This, Other>, HyperOperation2.WithOther<This, Other> {
        interface Math<This : HyperOperation.WithOther<This, Other>, Other> :
                HyperOperation1.WithOther.Math<This, Other>, HyperOperation2.WithOther.Math<This, Other>
    }
}

interface HyperOperation1<This> : Operation<This> {

    operator fun plus(other: This): This
    operator fun minus(other: This): This = plus(unaryMinus())
    operator fun unaryMinus(): This

    operator fun plus(other: Number) = plus(other.asThis())
    operator fun minus(other: Number) = minus(other.asThis())

    interface Math<This : HyperOperation1<This>> : Operation.Math<This> {
        operator fun Number.plus(other: This) = other.plus(this)
        operator fun Number.minus(other: This) = other.minus(this)
    }

    interface WithOther<This : HyperOperation1.WithOther<This, Other>, Other> :
            Operation.WithOther<This, Other>, HyperOperation1<This> {

        operator fun plus(other: Other) = plus(other.asThis())
        operator fun minus(other: Other) = minus(other.asThis())

        interface Math<This : HyperOperation1.WithOther<This, Other>, Other> : Operation.Math<This> {
            operator fun Other.plus(other: This) = other.plus(this)
            operator fun Other.minus(other: This) = other.minus(this)
        }
    }
}

interface HyperOperation2<This> : Operation<This> {

    operator fun times(other: This): This
    operator fun div(other: This): This = times(reciprocal())
    /*hold*/ fun reciprocal(): This

    operator fun times(other: Number) = times(other.asThis())
    operator fun div(other: Number) = div(other.asThis())

    interface Math<This : HyperOperation2<This>> : Operation.Math<This> {
        operator fun Number.times(other: This) = other.times(this)
        operator fun Number.div(other: This) = other.div(this)
    }

    interface WithOther<This : HyperOperation2.WithOther<This, Other>, Other> :
            Operation.WithOther<This, Other>, HyperOperation2<This> {

        interface Math<This : HyperOperation2.WithOther<This, Other>, Other> : Operation.Math<This> {
            operator fun Other.times(other: This) = other.times(this)
            operator fun Other.div(other: This) = other.div(this)
        }

        operator fun times(other: Other) = times(other.asThis())
        operator fun div(other: Other) = div(other.asThis())
    }
}
