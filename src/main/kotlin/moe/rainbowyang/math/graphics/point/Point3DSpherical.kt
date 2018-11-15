package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.number.*
import moe.rainbowyang.math.operation.cos
import moe.rainbowyang.math.operation.sin

/**
 * 三维球坐标系点
 * @author Rainbow Yang
 */
class Point3DSpherical(val r: Real, val theta: Real, val phi: Real) : Point() {
    
    constructor(r: Number = 0, θ: Number = 0, φ: Number = 0) : this(r.toReal(), θ.toReal(), φ.toReal())
    
    operator fun component1() = r
    operator fun component2() = theta
    operator fun component3() = phi
    
    companion object {
        operator fun invoke(form: Point): Point3DSpherical {
            val (x, y, z) = form.asAxes
            
            val r = form.length
            val theta = Math.acos((z / r).value).toReal()
            val phi = Point2D(x, y).angle
            
            return Point3DSpherical(r, theta, phi)
        }
    }
    
    fun spinAtTheta(angle: Real) = Point3DSpherical(r, theta + angle, phi)
    fun spinAtPhi(angle: Real) = Point3DSpherical(r, theta, phi + angle)
    
    override val asAxes = PointAxes(r * sin(theta) * cos(phi), r * sin(theta) * sin(phi), r * cos(theta))
    
    override fun toString(): String {
        return "Point3DSpherical(r=$r, theta=$theta, phi=$phi)"
    }
    
}
