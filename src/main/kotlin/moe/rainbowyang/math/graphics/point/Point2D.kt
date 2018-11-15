package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.number.atan2

/**
 * 二维笛卡尔坐标系点
 * @author Rainbow Yang
 */
data class Point2D(val x: Real, val y: Real) : Point() {
    
    constructor(point: PointAxes) : this(point[0], point[1])
    
    override val asAxes = PointAxes(x, y)
    
    val angle = atan2(y, x)
    
    /**
     * 逆时针旋转[angle]【弧度】
     */
    fun spin(angle: Real) = asPoint2DPolar.spin(angle).asPoint2D
    
}
