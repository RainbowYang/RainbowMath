package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.graphics.Graphics
import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.operation.Addition

/**
 * 抽象点
 * 所有子类的所有操作均不应修改其本身，而是返回一个新的类
 * 所有点之间都应能够进行互相转换，可以用[PointAxes]作为中介
 * @author Rainbow Yang
 */
abstract class Point : Graphics, Addition<Point> {
    
    /**
     * 转换为[PointAxes]
     */
    abstract val asAxes: PointAxes
    
    /**
     * 检测该点中是否没有[Double.NaN]之类无效的值
     */
    open val available: Boolean get() = asAxes.available
    
    /**
     * 默认通过[PointAxes]进行计算，值为其模
     */
    open val length: Real get() = asAxes.length
    
    override operator fun plus(other: Point): Point = asAxes.plus(other.asAxes)
    override operator fun unaryMinus(): Point = this * -Real.ONE
    
    open operator fun times(times: Real): Point = asAxes * times
    open operator fun div(div: Real): Point = this * div.reciprocal()
    
    override fun equals(other: Any?): Boolean = asAxes == other
    override fun hashCode(): Int = asAxes.hashCode()
}
