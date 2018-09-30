package moe.rainbowyang.math.operation

interface LogAndExp<This> : Operation<This>
        where This : LogAndExp<This>,
              This : HyperOperation2<This> {

    infix fun power(other: This): This = (this.ln() * other).exp()
    fun exp(): This

    infix fun log(other: This): This = other.ln() / this.ln()
    fun ln(): This

    interface Math<This> where This : LogAndExp<This>, This : HyperOperation2<This> {

        fun power(base: This, num: This) = base.power(num)
        fun exp(num: This) = num.exp()

        fun log(base: This, num: This) = base.log(num)
        fun ln(num: This) = num.ln()
    }
}
