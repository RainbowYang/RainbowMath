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
class PointAxes(vararg initValues: Double) : Point {

    //生成size维的值均为value的点
    constructor(value: Double, size: Int) : this(*DoubleArray(size) { value })

    companion object {
        operator fun invoke(initValues: DoubleArray) = PointAxes(*initValues)
        operator fun invoke(vararg initValues: Number) = this(initValues.asList())
        operator fun invoke(initValues: List<Number>) =
                invoke(DoubleArray(initValues.size) { initValues[it].toDouble() })
    }

    operator fun component1() = this[0]
    operator fun component2() = this[1]
    operator fun component3() = this[2]
    operator fun component4() = this[3]

    /**
     * 所有维度的值
     */
    val values = initValues

    /**
     * 维度数
     */
    val size get() = values.size

    operator fun get(index: Int) = values.getOrElse(index) { 0.0 }//维度不够时补0

    override val asAxes get() = this

    override val available get() = checkValues(values.asList())
    override val length get() = lengthOf(values.asList())

    override fun plus(other: Point): Point {
        val paOther = other.asAxes
        return PointAxes(DoubleArray(max(size, paOther.size)) { this[it] + paOther[it] })
    }

    override operator fun times(times: Double) = PointAxes(DoubleArray(size) { get(it) * times })


    fun plusAt(index: Int, plus: Number): PointAxes =
            PointAxes(createNewArrayWithOldData(index).apply { this[index] += plus.toDouble() })

    fun setAtAndNew(index: Int, value: Number): PointAxes =
            PointAxes(createNewArrayWithOldData(index).apply { this[index] = value.toDouble() })

    fun timesAtAndNew(index: Int, times: Number): PointAxes =
            PointAxes(createNewArrayWithOldData(index).apply { this[index] *= times.toDouble() })

    fun spinAtAndNew(firstIndex: Int, secondIndex: Int, angle: Number): PointAxes {
        val newValues = createNewArrayWithOldData(firstIndex, secondIndex)
        val (x, y) = Point2D(this[firstIndex], this[secondIndex]).asPoint2DPolar.spin(angle).asAxes
        newValues[firstIndex] = x
        newValues[secondIndex] = y

        return PointAxes(newValues)
    }

    private fun createNewArrayWithOldData(index1: Int, index2: Int) =
            createNewArrayWithOldData(max(index1, index2))

    private fun createNewArrayWithOldData(index: Int) = DoubleArray(max(index + 1, size)) { get(it) }

    override fun toString() = "PointForAxes(${Arrays.toString(values)})"


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

    override fun hashCode() = Arrays.hashCode(values)

}