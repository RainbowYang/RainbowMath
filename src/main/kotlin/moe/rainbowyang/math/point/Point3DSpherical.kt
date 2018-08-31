package moe.rainbowyang.math.point

/**
 * 三维球坐标系点
 * @author Rainbow Yang
 */
class Point3DSpherical(val r: Double, val theta: Double, val phi: Double) : Point {
    constructor(r: Number = 0, θ: Number = 0, φ: Number = 0) : this(r.toDouble(), θ.toDouble(), φ.toDouble())

    operator fun component1() = r
    operator fun component2() = theta
    operator fun component3() = phi

    companion object {
        operator fun invoke(form: Point): Point3DSpherical {
            val (x, y, z) = form.asAxes

            val r = form.length
            val theta = Math.acos(z / r)
            val phi = Point2D(x, y).angle

            return Point3DSpherical(r, theta, phi)
        }
    }

    fun spinAtTheta(angle: Double) = Point3DSpherical(r, theta + angle, phi)
    fun spinAtPhi(angle: Double) = Point3DSpherical(r, theta, phi + angle)

    override val asAxes by lazy {
        PointAxes(r * Math.sin(theta) * Math.cos(phi), r * Math.sin(theta) * Math.sin(phi), r * Math.cos(theta))
    }

    override fun toString(): String {
        return "Point3DSpherical(r=$r, theta=$theta, phi=$phi)"
    }
}