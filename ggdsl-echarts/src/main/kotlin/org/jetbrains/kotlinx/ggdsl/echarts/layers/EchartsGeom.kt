/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

public data class EchartsGeom(val name: String) : Geom


public val POINT: EchartsGeom = EchartsGeom("point")
public val BAR: EchartsGeom = EchartsGeom("bar")

@PublishedApi
internal val LINE: EchartsGeom = EchartsGeom("line")