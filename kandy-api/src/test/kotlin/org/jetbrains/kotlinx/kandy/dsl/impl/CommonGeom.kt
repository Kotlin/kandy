/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.ir.geom.Geom

data class CommonGeom(val name: String) : Geom


val POINT = CommonGeom("point")
val BAR = CommonGeom("bar")
val LINE = CommonGeom("line")
