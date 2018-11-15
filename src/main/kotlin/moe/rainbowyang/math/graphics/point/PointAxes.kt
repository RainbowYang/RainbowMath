package moe.rainbowyang.math.graphics.point

import moe.rainbowyang.math.number.*
import moe.rainbowyang.math.until
import java.util.*
import kotlin.math.max


/**
 * 任意纬度的坐标轴点
 * @author Rainbow Yang
 */
class PointAxes(val values: List<Real>) : Point() {
    
    constructor(vararg values: Real) : this(List(values.size) { values[it] })
    
    /**
     * 生成size维的值均为value的点
     */
    constructor(value: Real, size: Int) : this(List(size) { value })
    
    constructor(value: Number, size: Int) : this(List(size) { value.toReal() })
    
    /**
     * 维度数
     */
    val size = values.size
    
    operator fun component1() = values[0]
    operator fun component2() = values[1]
    operator fun component3() = values[2]
    operator fun component4() = values[3]
    
    operator fun get(index: Int) = values.getOrElse(index) { Real.ZERO } //维度不够时补0
    
    override val asAxes = this
    
    override val available = values.checkValues()
    override val length = values.lengthOf()
    
    override fun plus(other: Point): Point {
        val paOther = other.asAxes
        return PointAxes(List(max(size, paOther.size)) { this[it] + paOther[it] })
    }
    
    override operator fun times(times: Real) = PointAxes(List(size) { get(it) * times })
    
    
    fun plusAt(index: Int, plus: Real): PointAxes =
            PointAxes(createNewListWithOldData(index).apply { this[index] += plus })
    
    fun setAtAndNew(index: Int, value: Number): PointAxes =
            PointAxes(createNewListWithOldData(index).apply { this[index] = value.toReal() })
    
    fun timesAtAndNew(index: Int, times: Number): PointAxes =
            PointAxes(createNewListWithOldData(index).apply { this[index] *= times.toReal() })
    
    fun spinAtAndNew(firstIndex: Int, secondIndex: Int, angle: Real): PointAxes {
        val newValues = createNewListWithOldData(firstIndex, secondIndex)
        val (x, y) = Point2D(this[firstIndex], this[secondIndex]).asPoint2DPolar.spin(angle).asAxes
        newValues[firstIndex] = x
        newValues[secondIndex] = y
        
        return PointAxes(newValues)
    }
    
    private fun createNewListWithOldData(index1: Int, index2: Int) =
            createNewListWithOldData(max(index1, index2))
    
    private fun createNewListWithOldData(index: Int) = MutableList(max(index + 1, size)) { get(it) }
    
    override fun toString() = "PointForAxes(${Arrays.toString(values.toTypedArray())})"
    
    
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
        return Arrays.hashCode(values.toTypedArray())
    }
    
}
