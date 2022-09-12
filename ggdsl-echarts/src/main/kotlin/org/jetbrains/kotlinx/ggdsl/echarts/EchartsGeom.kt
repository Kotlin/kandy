/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

data class EchartsGeom(val name: String) : Geom


val POINT = EchartsGeom("point")
val BAR = EchartsGeom("bar")
val LINE = EchartsGeom("line")