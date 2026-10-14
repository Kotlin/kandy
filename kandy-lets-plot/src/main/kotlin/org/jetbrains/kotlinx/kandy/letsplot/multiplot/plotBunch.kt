package org.jetbrains.kotlinx.kandy.letsplot.multiplot

import org.jetbrains.kotlinx.kandy.letsplot.multiplot.context.PlotBunchBuffer
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch

/**
 * Collection of plots.
 * Each plot can have an arbitrary location and size.
 * Creates [PlotBunchBuffer] with method [PlotBunchBuffer.add] that adds a plot with the specified coordinates
 * of the upper left point and size.
 *
 * ## Website Examples
 *
 * - [PlotBunch Examples](https://kotlin.github.io/kandy/plotbunch-api.html)
 * - [Kandy Gallery](https://kotlin.github.io/kandy/examples.html)
 *
 * @return [PlotBunch] with given plots.
 */
public inline fun plotBunch(block: PlotBunchBuffer.() -> Unit): PlotBunch {
    return PlotBunch(PlotBunchBuffer().apply(block).items)
}