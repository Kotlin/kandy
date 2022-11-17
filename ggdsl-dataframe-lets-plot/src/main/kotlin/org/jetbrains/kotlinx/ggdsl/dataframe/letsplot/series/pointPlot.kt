package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.series

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dataframe.DataFrameWrapper
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.position.Position
import org.jetbrains.kotlinx.ggdsl.letsplot.series.PointSeriesPlotContext

public inline fun DataFrame<*>.pointPlot(
    position: Position = Position.Identity,
    block: PointSeriesPlotContext.() -> Unit,
): Plot {
    return PointSeriesPlotContext(DataFrameWrapper(this), position).apply(block).toPlot()
}
