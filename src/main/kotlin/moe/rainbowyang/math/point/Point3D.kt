package moe.rainbowyang.math.point

/**
 * 三维轴坐标点
 * @author Rainbow Yang
 */
data class Point3D(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) : Point {
    
    constructor(x: Number = 0, y: Number = 0, z: Number = 0) : this(x.toDouble(), y.toDouble(), z.toDouble())
    
    companion object {
        operator fun invoke(form: Point): Point3D {
            val (x, y, z) = form.asAxes
            return Point3D(x, y, z)
        }
    }
    
    override val asAxes by lazy { PointAxes(x, y, z) }
    
    fun spinAtXY(angle: Number) = asAxes.spinAtAndNew(0, 1, angle)
    fun spinAtXZ(angle: Number) = asAxes.spinAtAndNew(0, 2, angle)
    fun spinAtYX(angle: Number) = asAxes.spinAtAndNew(1, 0, angle)
    fun spinAtYZ(angle: Number) = asAxes.spinAtAndNew(1, 2, angle)
    fun spinAtZX(angle: Number) = asAxes.spinAtAndNew(2, 0, angle)
    fun spinAtZY(angle: Number) = asAxes.spinAtAndNew(2, 1, angle)
    
}
