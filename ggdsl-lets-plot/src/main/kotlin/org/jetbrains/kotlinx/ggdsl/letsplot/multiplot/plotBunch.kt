/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.ir.Plot

@Serializable
public data class PlotBunch(
    val items: List<Item>
) {
    @Serializable
    public class Item(
        public val plot: Plot,
        public val x: Int,
        public val y: Int,
        public val width: Int?,
        public val height: Int?
    )
}

public class PlotBunchBuffer {
    @PublishedApi
    internal val items: MutableList<PlotBunch.Item> = mutableListOf()

    public fun add(plot: Plot, x: Int, y: Int, width: Int? = null, height: Int? = null) {
        items.add(PlotBunch.Item(plot, x, y, width, height))
    }
}

public inline fun plotBunch(block: PlotBunchBuffer.() -> Unit): PlotBunch {
    return PlotBunch(PlotBunchBuffer().apply(block).items)
}
