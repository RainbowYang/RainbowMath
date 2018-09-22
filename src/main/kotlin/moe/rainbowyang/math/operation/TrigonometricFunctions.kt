package moe.rainbowyang.math.operation

interface TrigonometricFunctions<This : HyperOperation2<This>> : Operation<This> {
    fun sin(): This
    fun cos(): This
    fun tan(): This = sin() / cos()
    fun cot(): This = cos() / sin()
    fun sec(): This = cos().reciprocal()
    fun csc(): This = sin().reciprocal()
}

/** 此接口应仅由object实现 */
interface MathForTrigonometricFunctions<This : HyperOperation2<This>> {
    fun sin(num: TrigonometricFunctions<This>): This = num.sin()
    fun cos(num: TrigonometricFunctions<This>): This = num.cos()
    fun tan(num: TrigonometricFunctions<This>): This = num.tan()
    fun cot(num: TrigonometricFunctions<This>): This = num.cot()
    fun sec(num: TrigonometricFunctions<This>): This = num.sec()
    fun csc(num: TrigonometricFunctions<This>): This = num.csc()
}