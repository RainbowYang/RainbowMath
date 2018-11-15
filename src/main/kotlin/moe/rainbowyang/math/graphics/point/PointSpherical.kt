package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.number.Real
import moe.rainbowyang.math.operation.cos
import moe.rainbowyang.math.operation.sin
import java.util.*


/**
 * 任意维的球坐标点
 * 由一个radius和多个angle组成
 *
 * @author Rainbow Yang
 */
data class PointSpherical(val radius: Real, val angles: List<Real>) : Point() {
    
    companion object {
        operator fun invoke(cp: Point): PointSpherical {
            val values = cp.asAxes.values
            
            var radius = values[0]
            val angles = mutableListOf<Real>()
            
            (1 until values.size).forEach {
                val height = values[it]
                val (r, angle) = Point2D(radius, height).asPoint2DPolar
                
                radius = r
                angles.add(angle)
            }
            
            return PointSpherical(radius, angles.toList())
        }
    }
    
    override val asAxes = toAxes()
    
    fun toAxes(): PointAxes {
        val values = mutableListOf<Real>()
        var rest = radius
        angles.reversed().forEach {
            values.add(rest * sin(it))
            rest *= cos(it)
        }
        values.add(rest)
        values.reverse()
        return PointAxes(values)
    }
    
    override fun toString(): String {
        return "PointSpherical(radius=$radius, angles=${Arrays.toString(angles.toTypedArray())})"
    }
    
}
