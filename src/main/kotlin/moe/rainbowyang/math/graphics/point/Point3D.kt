package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.number.toReal

/**
 * 三维轴坐标点
 * @author Rainbow Yang
 */
data class Point3D(val x: Real, val y: Real, val z: Real) : Point() {
    
    constructor(x: Number = 0, y: Number = 0, z: Number = 0) : this(x.toReal(), y.toReal(), z.toReal())
    
    companion object {
        operator fun invoke(form: Point): Point3D {
            val (x, y, z) = form.asAxes
            return Point3D(x, y, z)
        }
    }
    
    override val asAxes = PointAxes(x, y, z)
    
    fun spinAtXY(angle: Real) = asAxes.spinAtAndNew(0, 1, angle)
    fun spinAtXZ(angle: Real) = asAxes.spinAtAndNew(0, 2, angle)
    fun spinAtYX(angle: Real) = asAxes.spinAtAndNew(1, 0, angle)
    fun spinAtYZ(angle: Real) = asAxes.spinAtAndNew(1, 2, angle)
    fun spinAtZX(angle: Real) = asAxes.spinAtAndNew(2, 0, angle)
    fun spinAtZY(angle: Real) = asAxes.spinAtAndNew(2, 1, angle)
    
}
