package moe.rainbowyang.math.operation

interface LogAndExp<This> : Operation<This>
        where This : LogAndExp<This>,
              This : HyperOperation2<This> {

    infix fun power(other: This): This = (this.ln() * other).exp()
    infix fun power(other: Number): This = (this.ln() * other).exp()
    fun exp(): This

    infix fun log(other: This): This = other.ln() / this.ln()
    infix fun log(other: Number): This = other.asThis().ln() / this.ln()
    fun ln(): This

    interface Math<This> : Operation.Math<This> where This : LogAndExp<This>,
                                                      This : HyperOperation2<This> {
        fun power(base: This, num: This) = base.power(num)
        fun power(base: This, num: Number) = base.power(num)
        fun power(base: Number, num: This) = with(ZERO) { base.asThis().power(num) }
        fun power(base: Number, num: Number) = with(ZERO) { base.asThis().power(num) }
        fun exp(num: This) = num.exp()

        fun log(base: This, num: This) = base.log(num)
        fun log(base: This, num: Number) = base.log(num)
        fun log(base: Number, num: This) = with(ZERO) { base.asThis().log(num) }
        fun log(base: Number, num: Number) = with(ZERO) { base.asThis().log(num) }
        fun ln(num: This) = num.ln()
    }

    interface WithOther<This, Other> : LogAndExp<This>, Operation.WithOther<This, Other>
            where This : LogAndExp.WithOther<This, Other>,
                  This : HyperOperation2<This> {

        fun power(num: Other): This = power(num.asThis())
        infix fun Other.power(num: This): This = asThis().power(num)
        infix fun Other.power(num: Other): This = asThis().power(num)

        fun log(num: Other): This = log(num.asThis())
        infix fun Other.log(num: This): This = asThis().log(num)
        infix fun Other.log(num: Other): This = asThis().log(num)

        interface Math<This, Other> : Operation.Math<This> where This : LogAndExp.WithOther<This, Other>,
                                                                 This : HyperOperation2<This> {
            fun power(base: Other, num: This): This = with(ZERO) { base.asThis().power(num) }
            fun power(base: Other, num: Other): This = with(ZERO) { base.asThis().power(num.asThis()) }

            fun log(base: Other, num: This): This = with(ZERO) { base.asThis().log(num) }
            fun log(base: Other, num: Other): This = with(ZERO) { base.asThis().log(num.asThis()) }
        }
    }

}
