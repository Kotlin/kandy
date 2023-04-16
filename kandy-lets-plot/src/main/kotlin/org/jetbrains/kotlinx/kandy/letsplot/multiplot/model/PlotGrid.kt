/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.multiplot.model

import org.jetbrains.kotlinx.kandy.ir.Plot

public data class PlotGrid(
    val plots: List<Plot?>,
    val nCol: Int?,
    val widths: List<Number>?,
    val heights: List<Number>?,
    val hspace: Number?,
    val vspace: Number?,
    val fit: Boolean,
    val align: Boolean,
)
