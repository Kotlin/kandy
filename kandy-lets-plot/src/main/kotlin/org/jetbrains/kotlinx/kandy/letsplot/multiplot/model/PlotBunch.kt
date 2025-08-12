/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.multiplot.model

import org.jetbrains.kotlinx.kandy.ir.Plot

public data class PlotBunch(
    val items: List<Item>
) {
    public class Item(
        public val plot: Plot,
        public val x: Double,
        public val y: Double,
        public val width: Double,
        public val height: Double,
        public val dx: Int,
        public val dy: Int,
    )
}