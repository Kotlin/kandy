package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.SmoothContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.smooth
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.SmoothMethod

public inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
): Unit = smooth(sourceX.toDataSource(), sourceY, method, pointsNumber, se, level, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
): Unit = smooth(sourceX, sourceY.toDataSource(), method, pointsNumber, se, level, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
): Unit = smooth(sourceX.toDataSource(), sourceY.toDataSource(), method, pointsNumber, se, level, block)
