package org.jetbrains.kotlinx.ggdsl.letsplot.multiplot

import org.jetbrains.kotlinx.ggdsl.ir.Plot

data class PlotBunch(
    val items: List<Item>
) {
     class Item(
        val plot: Plot,
        val x: Int,
        val y: Int,
        val width: Int?,
        val height: Int?
    )
}

class PlotBunchBuffer {
    @PublishedApi
    internal val items: MutableList<PlotBunch.Item> = mutableListOf()

    fun add(plot: Plot, x: Int, y: Int, width: Int?, height: Int? = null) {
        items.add(PlotBunch.Item(plot, x, y, width, height))
    }
}

inline fun plotBunch(block: PlotBunchBuffer.() -> Unit): PlotBunch {
    return PlotBunch(PlotBunchBuffer().apply(block).items)
}
