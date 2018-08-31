package moe.rainbowyang.math.point

import moe.rainbowyang.math.util.Line

/**
 * 二维双角坐标点
 * @author Rainbow Yang
 */
data class Point2DBiangular(val angle1: Double, val angle2: Double, val a: Double) : Point {

    override val asAxes by lazy { (Line(Point2D(a, 0), angle1) crossTo Line(Point2D(-a, 0), angle2)).asAxes }

}