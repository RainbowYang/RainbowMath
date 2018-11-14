package moe.rainbowyang.math.point

import kotlin.math.*

/**
 * 二维双曲坐标点
 * @author Rainbow Yang
 */
data class Point2DHyperbolic(val u: Double, val v: Double) : Point {
    
    companion object {
        operator fun invoke(from: PointAxes): Point2DHyperbolic {
            val (x, y) = from
            return Point2DHyperbolic(-0.5 * ln(y / x), sqrt(x * y))
        }
    }
    
    override val asAxes by lazy { PointAxes(v * E.pow(u), v * -E.pow(u)) }
    
    override fun toString() = "Point2DHyperbolic(u=$u, v=$v)"
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        
        other as Point2DHyperbolic
        
        if (u != other.u) return false
        if (v != other.v) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = u.hashCode()
        result = 31 * result + v.hashCode()
        return result
    }
    
}
