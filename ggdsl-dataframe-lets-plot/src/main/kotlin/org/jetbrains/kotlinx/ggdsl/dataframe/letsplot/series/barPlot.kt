package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.series

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dataframe.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.series.BarSeriesPlotContext

public inline fun DataFrame<*>.barPlot(
    position: Position = Position.Identity,
    block: BarSeriesPlotContext.() -> Unit,
): Plot {
    return BarSeriesPlotContext(DataFrameWrapper(this), position).apply(block).toPlot()
}
