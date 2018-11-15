package moe.rainbowyang.math.graphics.point

/*
 * 这里放置所有的点转换为其他点的方法，但其逻辑应写在其对应的类中
 */

val Point.asPoint2D get() = asAxes.asPoint2D
val PointAxes.asPoint2D get() = Point2D(this)

val Point.asPoint2DPolar get() = asAxes.asPoint2DPolar
val PointAxes.asPoint2DPolar get() = Point2DPolar(this)

val Point.asPoint3D get() = asAxes.asPoint3D
val PointAxes.asPoint3D get() = Point3D(this)

val Point.asPoint3DSpherical get() = asAxes.asPoint3DSpherical
val PointAxes.asPoint3DSpherical get() = Point3DSpherical(this)

val Point.asPointSpherical get() = asAxes.asPointSpherical
val PointAxes.asPointSpherical get() = PointSpherical(this)
