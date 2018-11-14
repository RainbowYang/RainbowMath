package moe.rainbowyang.math.point

import kotlin.math.sqrt

/**
 * 二维抛物线坐标点
 * @author Rainbow Yang
 */
class Point2DParabolic(val σ: Double, val τ: Double) : Point {
    
    operator fun component1() = σ
    operator fun component2() = τ
    
    private val c1 get() = σ
    private val c2 get() = τ
    
    companion object {
        operator fun invoke(form: PointAxes): Point2DParabolic {
            val (x, y) = form
            return Point2DParabolic(sqrt(-y + sqrt(x * x + y * y)), sqrt(y + sqrt(x * x + y * y)))
        }
    }
    
    override val asAxes by lazy { PointAxes(c1 * c2, 0.5 * (c1 * c1 + c2 * c2)) }
    
    override fun toString(): String {
        return "Point2DParabolic(σ=$σ, τ=$τ)"
    }
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        
        other as Point2DParabolic
        
        if (σ != other.σ) return false
        if (τ != other.τ) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = σ.hashCode()
        result = 31 * result + τ.hashCode()
        return result
    }
    
}

