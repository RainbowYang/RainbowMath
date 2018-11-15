package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.number.toReal
import moe.rainbowyang.math.operation.cos
import moe.rainbowyang.math.operation.sin

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */
data class Point2DPolar(val radius: Real, val angle: Real) : Point() {
    
    constructor(radius: Number, angle: Number) : this(radius.toReal(), angle.toReal())
    
    companion object {
        operator fun invoke(form: Point): Point2DPolar {
            val pd = form.asPoint2D
            return Point2DPolar(pd.length, pd.angle)
        }
    }
    
    override val asAxes by lazy { PointAxes(radius * cos(angle), radius * sin(angle)) }
    
    /**
     * 逆时针旋转[angle]【弧度】
     */
    fun spin(angle: Real) = Point2DPolar(radius, this.angle + angle)
    
}

