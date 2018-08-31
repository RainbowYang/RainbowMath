package moe.rainbowyang.math.point

/**
 * 表示坐标系上的一个点
 *
 * 所有子类的所有操作均不应修改其本身，而是返回一个新的类
 *
 * 所有点之间都应能够进行互相转换，可以用[PointAxes]作为中介
 *
 * @author Rainbow Yang
 */
interface Point {

    companion object {
        val ZERO = PointAxes()
    }

    /**
     * 转换为[PointAxes]
     */
    val asAxes: PointAxes

    /**
     * 检测该点中是否没有[Double.NaN]之类无效的值
     */
    val available: Boolean get() = asAxes.available

    /**
     * 默认通过[PointAxes]进行计算，值为其模
     */
    val length: Double get() = asAxes.length

    operator fun plus(other: Point): Point = asAxes.plus(other.asAxes)
    operator fun times(times: Double): Point = asAxes * times

    operator fun minus(other: Point) = plus(-other)
    operator fun unaryMinus() = this * -1

    operator fun times(times: Number): Point = this * times.toDouble()
    operator fun div(div: Number): Point = this * (1 / div.toDouble())

}