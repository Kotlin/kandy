/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.impl

import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

data class CommonGeom(val name: String) : Geom


val POINT = CommonGeom("point")
val BAR = CommonGeom("bar")
val LINE = CommonGeom("line")
