package moe.rainbowyang.math.graphics.line

import moe.rainbowyang.math.almostEquals
import moe.rainbowyang.math.graphics.point.Point2D
import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.number.toReal
import kotlin.math.atan

/**
 * 数学意义上的线
 * 提供求交点等功能
 * 表达式为ax+by+c=0
 * @author Rainbow Yang
 */
class Line(val a: Real, val b: Real, val c: Real) {
    
    /** 斜率 */
    val slope = -a / b
    /** 倾斜角 */
    val angle = atan(slope.value).toReal()
    
    constructor(a: Number, b: Number, c: Number) : this(a.toReal(), b.toReal(), c.toReal())
    
    companion object {
        /**
         * 根据两个点生成线
         */
        operator fun invoke(p1: Point2D, p2: Point2D): Line {
            val a = p1.y - p2.y
            val b = -(p1.x - p2.x)
            val c = p1.x * p2.y - p1.y * p2.x
            return Line(a, b, c)
        }
        
        /**
         * 根据一个点和倾斜角生成线
         */
        operator fun invoke(point: Point2D, angle: Double) =
                if ((angle - Math.PI / 2) % Math.PI almostEquals 0.0) {
                    Line(1, 0, -point.x.value)
                } else {
                    val a = Math.tan(angle)
                    Line(a, -1, point.y.value * (1 - a))
                }
        
        val X_AXIS = Line(0.0, 1.0, 0.0)
        val Y_AXIS = Line(1.0, 0.0, 0.0)
        operator fun invoke(point: Point2D, angle: Real) = invoke(point, angle.value)
    }
    
    /**
     * 得到两条直线的交点
     * @throws NoCrossException 当两条[Line]平行的时候抛出此异常
     */
    infix fun crossTo(other: Line): Point2D {
        //平行
        if (a * other.b - b * other.a == Real.ZERO)
            throw NoCrossException("$this has no cross with $other")
        
        return Point2D(-(c * other.b - b * other.c) / (a * other.b - b * other.a),
                -(a * other.c - c * other.a) / (a * other.b - b * other.a))
    }
    
    override fun toString(): String {
        return "Line(${a}x+${b}y+$c=0)"
    }
    
    class NoCrossException(message: String) : RuntimeException(message)
}
