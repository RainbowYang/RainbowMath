package moe.rainbowyang.math.operation

/**
 * @author Rainbow Yang
 */
interface ExpAndLog<This : ExpAndLog<This, Out>, Out : HyperOperation2<Out>> : Operation<This> {
    fun pow(other: This): Out = exp(this.ln() * other.asOut())

    fun exp(): Out

    fun exp(out: Out): Out
    fun asOut(): Out

    fun log(other: This): Out = other.ln() / this.ln()
    fun ln(): Out
}
