package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

data class CommonGeom(val name: String) : Geom


val POINT = CommonGeom("point")
val BAR = CommonGeom("bar")
val LINE = CommonGeom("line")
