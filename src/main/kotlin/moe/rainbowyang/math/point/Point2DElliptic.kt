package moe.rainbowyang.math.point

import kotlin.math.*

/**
 * 二维椭圆坐标点
 * @author Rainbow Yang
 */
data class Point2DElliptic(val μ: Double, val ν: Double, val a: Double) : Point {

    private val c1 get() = μ
    private val c2 get() = ν

    override val asAxes by lazy { PointAxes(a * cosh(c1) * cos(c2), a * sinh(c1) * sin(c2)) }
}