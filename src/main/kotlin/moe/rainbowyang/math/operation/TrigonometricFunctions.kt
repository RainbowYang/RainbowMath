package moe.rainbowyang.math.operation

interface TrigonometricFunctions<This : HyperOperation2<This>> : Operation<This> {

    interface Math<This> where This : TrigonometricFunctions<This>,
                               This : HyperOperation2<This> {
        fun sin(num: This): This = num.sin()
        fun cos(num: This): This = num.cos()
        fun tan(num: This): This = num.tan()
        fun cot(num: This): This = num.cot()
        fun sec(num: This): This = num.sec()
        fun csc(num: This): This = num.csc()
    }

    fun sin(): This
    fun cos(): This
    fun tan(): This = sin() / cos()
    fun cot(): This = cos() / sin()
    fun sec(): This = cos().reciprocal()
    fun csc(): This = sin().reciprocal()
}