package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

data class EchartsGeom(val name: String) : Geom


val POINT = EchartsGeom("point")
val BAR = EchartsGeom("bar")
val LINE = EchartsGeom("line")