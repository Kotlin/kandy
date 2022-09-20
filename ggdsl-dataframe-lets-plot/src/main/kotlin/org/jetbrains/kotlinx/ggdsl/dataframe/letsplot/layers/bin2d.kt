package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.Bin2DContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.bin2D
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins2D

public inline fun <reified T : Any, reified R : Any> PlotContext.bin2D(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
): Unit = bin2D(sourceX.toDataSource(), sourceY, bins, drop, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.bin2D(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
): Unit = bin2D(sourceX.toDataSource(), sourceY.toDataSource(), bins, drop, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.bin2D(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
): Unit = bin2D(sourceX, sourceY.toDataSource(), bins, drop, block)
