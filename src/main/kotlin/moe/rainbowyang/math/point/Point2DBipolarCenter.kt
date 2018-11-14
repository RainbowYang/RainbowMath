package moe.rainbowyang.math.point

import kotlin.math.*

/**
 * 二维双心坐标点
 * @author Rainbow Yang
 */
data class Point2DBipolarCenter(val r1: Double, val r2: Double, val a: Double) : Point {
    
    override val asAxes by lazy {
        val d = (r1 * r1 - r2 * r2)
        val x = d / (4 * a)
        val y = sqrt(16 * a * a * r1 * r1 - (d + 4 * a * a).pow(2.0)) / (4 * a)
        PointAxes(x, y)
    }
    
}
