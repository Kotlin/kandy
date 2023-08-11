/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.ir.geom.Geom

internal data class CommonGeom(val name: String) : Geom

internal val POINT = CommonGeom("point")
internal val BAR = CommonGeom("bar")
internal val LINE = CommonGeom("line")
