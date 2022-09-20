package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.QQ2Context
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.qq2

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    block: QQ2Context.() -> Unit
): Unit = qq2(sourceX.toDataSource(), sourceY, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    block: QQ2Context.() -> Unit
): Unit = qq2(sourceX, sourceY.toDataSource(), block)

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    block: QQ2Context.() -> Unit
): Unit = qq2(sourceX.toDataSource(), sourceY.toDataSource(), block)