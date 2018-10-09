package moe.rainbowyang.math.operation

interface HyperbolicFunction<This : HyperOperation2<This>> : Operation<This> {

    fun sinh(): This
    fun cosh(): This
    fun tanh(): This = sinh() / cosh()
    fun coth(): This = cosh() / sinh()
    fun sech(): This = cosh().reciprocal()
    fun csch(): This = sinh().reciprocal()

    interface Math<This> where This : HyperbolicFunction<This>,
                               This : HyperOperation2<This> {
        fun sinh(num: This): This = num.sinh()
        fun cosh(num: This): This = num.cosh()
        fun tanh(num: This): This = num.tanh()
        fun coth(num: This): This = num.coth()
        fun sech(num: This): This = num.sech()
        fun csch(num: This): This = num.csch()
    }
}