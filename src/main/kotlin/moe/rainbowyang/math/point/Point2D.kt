package moe.rainbowyang.math.point

/**
 * 二维笛卡尔坐标系点
 * @author Rainbow Yang
 */
data class Point2D(val x: Double, val y: Double) : Point {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(point: PointAxes) : this(point[0], point[1])

    override val asAxes by lazy { PointAxes(x, y) }

    val angle get() = Math.atan2(y, x)

    /**
     * 逆时针旋转[angle]【弧度】
     */
    fun spin(angle: Double) = asPoint2DPolar.spin(angle).asPoint2D

}