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
        public val x: Int,
        public val y: Int,
        public val width: Int?,
        public val height: Int?
    )
}