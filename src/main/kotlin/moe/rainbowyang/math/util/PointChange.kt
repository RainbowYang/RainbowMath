package moe.rainbowyang.math.util

import moe.rainbowyang.math.point.*

/*
 * 这里放置所有的点转换为其他点的方法，但其逻辑应写在其对应的类中
 */

val PointAxes.asPoint2D get() = Point2D(this)
val PointAxes.asPoint2DPolar get() = Point2DPolar(this)
val PointAxes.asPoint2DHyperbolic get() = Point2DHyperbolic(this)
val PointAxes.asPoint2DParabolic get() = Point2DParabolic(this)
val PointAxes.asPoint3D get() = Point3D(this)
val PointAxes.asPoint3DSpherical get() = Point3DSpherical(this)
val PointAxes.asPointSpherical get() = PointSpherical(this)

val Point.asPoint2D get() = asAxes.asPoint2D
val Point.asPoint2DPolar get() = asAxes.asPoint2DPolar
val Point.asPoint2DHyperbolic get() = asAxes.asPoint2DHyperbolic
val Point.asPoint2DParabolic get() = asAxes.asPoint2DParabolic
val Point.asPoint3D get() = asAxes.asPoint3D
val Point.asPoint3DSpherical get() = asAxes.asPoint3DSpherical
val Point.asPointSpherical get() = asAxes.asPointSpherical
