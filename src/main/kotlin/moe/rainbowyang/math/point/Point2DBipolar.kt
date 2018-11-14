package moe.rainbowyang.math.point

import kotlin.math.*

/**
 * 二维双极坐标点
 * @author Rainbow Yang
 */
data class Point2DBipolar(val σ: Double, val τ: Double, val a: Double) : Point {
    
    private val c1 get() = σ
    private val c2 get() = τ
    
    override val asAxes by lazy {
        val base = a / (cosh(c2) - cos(c1))
        PointAxes(sinh(c2) * base, sin(c1) * base)
    }
    
}

