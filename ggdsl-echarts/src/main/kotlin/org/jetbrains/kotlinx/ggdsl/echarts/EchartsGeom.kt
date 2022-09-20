package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

public data class EchartsGeom(val name: String) : Geom


public val POINT: EchartsGeom = EchartsGeom("point")
public val BAR: EchartsGeom = EchartsGeom("bar")
public val LINE: EchartsGeom = EchartsGeom("line")