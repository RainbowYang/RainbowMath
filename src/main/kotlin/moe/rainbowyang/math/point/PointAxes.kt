package moe.rainbowyang.math.point

import moe.rainbowyang.math.checkValues
import moe.rainbowyang.math.lengthOf
import moe.rainbowyang.math.until
import java.util.*
import kotlin.math.max

/**
 * 任意纬度的坐标轴点
 *
 * @author Rainbow Yang
 */
class PointAxes(val values: List<Double>) : Point {
    constructor(vararg values: Number) : this(List(values.size) { values[it].toDouble() })

    //生成size维的值均为value的点
    constructor(value: Double, size: Int) : this(List(size) { value })

    /**
     * 维度数
     */
    val size = values.size

    operator fun component1() = values[0]
    operator fun component2() = values[1]
    operator fun component3() = values[2]
    operator fun component4() = values[3]
    operator fun component5() = values[4]

    operator fun get(index: Int) = values.getOrElse(index) { 0.0 }//维度不够时补0

    override val asAxes get() = this

    override val available get() = values.checkValues()
    override val length get() = values.lengthOf()

    override fun plus(other: Point): Point {
        val paOther = other.asAxes
        return PointAxes(List(max(size, paOther.size)) { this[it] + paOther[it] })
    }

    override operator fun times(times: Double) = PointAxes(List(size) { get(it) * times })


    fun plusAt(index: Int, plus: Number): PointAxes =
            PointAxes(createNewListWithOldData(index).apply { this[index] += plus.toDouble() })

    fun setAtAndNew(index: Int, value: Number): PointAxes =
            PointAxes(createNewListWithOldData(index).apply { this[index] = value.toDouble() })

    fun timesAtAndNew(index: Int, times: Number): PointAxes =
            PointAxes(createNewListWithOldData(index).apply { this[index] *= times.toDouble() })

    fun spinAtAndNew(firstIndex: Int, secondIndex: Int, angle: Number): PointAxes {
        val newValues = createNewListWithOldData(firstIndex, secondIndex)
        val (x, y) = Point2D(this[firstIndex], this[secondIndex]).asPoint2DPolar.spin(angle).asAxes
        newValues[firstIndex] = x
        newValues[secondIndex] = y

        return PointAxes(newValues)
    }

    private fun createNewListWithOldData(index1: Int, index2: Int) =
            createNewListWithOldData(max(index1, index2))

    private fun createNewListWithOldData(index: Int) = MutableList(max(index + 1, size)) { get(it) }

    override fun toString() = "PointForAxes(${Arrays.toString(values.toDoubleArray())})"


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as PointAxes

        //只要两个点不为0的值均相等即可 这意味着(1,1,0) equals (1,1,0,0) = true
        until(max(this.size, other.size)).forEach {
            if (this[it] != other[it]) return false
        }

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(values.toDoubleArray())
    }
}