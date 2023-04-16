/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.layers

import org.jetbrains.kotlinx.kandy.ir.geom.Geom

internal data class EchartsGeom(val name: String) : Geom

@PublishedApi
internal val LINE: EchartsGeom = EchartsGeom("line")

@PublishedApi
internal val AREA: EchartsGeom = EchartsGeom("area")

@PublishedApi
internal val BAR: EchartsGeom = EchartsGeom("bar")

@PublishedApi
internal val PIE: EchartsGeom = EchartsGeom("pie")

@PublishedApi
internal val POINT: EchartsGeom = EchartsGeom("point")

@PublishedApi
internal val CANDLESTICK: EchartsGeom = EchartsGeom("candlestick")

@PublishedApi
internal val BOXPLOT: EchartsGeom = EchartsGeom("boxplot")
