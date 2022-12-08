/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom

@Serializable
public data class LetsPlotGeom(val name: String) : Geom
