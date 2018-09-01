package moe.rainbowyang.math.point

import java.util.*


/**
 * 任意维的球坐标点
 * 由一个radius和多个angle组成
 *
 * @author Rainbow Yang
 */
data class PointSpherical(val radius: Double, val angles: List<Double>) : Point {

    companion object {
        operator fun invoke(cp: Point): PointSpherical {
            val values = cp.asAxes.values

            var radius = values[0]
            val angles = mutableListOf<Double>()

            (1 until values.size).forEach {
                val height = values[it]
                val (r, angle) = Point2D(radius, height).asPoint2DPolar

                radius = r
                angles.add(angle)
            }

            return PointSpherical(radius, angles.toList())

        }
    }

    override val asAxes by lazy {
        val values = mutableListOf<Double>()

        var rest = radius

        angles.reversed().forEach {
            values.add(rest * Math.sin(it))
            rest *= Math.cos(it)
        }

        values.add(rest)

        values.reverse()

        PointAxes(values)
    }

    override fun toString(): String {
        return "PointSpherical(radius=$radius, angles=${Arrays.toString(angles.toDoubleArray())})"
    }


}