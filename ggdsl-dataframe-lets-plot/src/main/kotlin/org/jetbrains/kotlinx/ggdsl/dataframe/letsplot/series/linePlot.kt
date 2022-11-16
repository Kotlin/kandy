package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.series

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dataframe.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.series.LineSeriesPlotContext

public inline fun DataFrame<*>.linePlot(
    position: Position = Position.Identity,
    block: LineSeriesPlotContext.() -> Unit,
): Plot {
    return LineSeriesPlotContext(DataFrameWrapper(this), position).apply(block).toPlot()
}
