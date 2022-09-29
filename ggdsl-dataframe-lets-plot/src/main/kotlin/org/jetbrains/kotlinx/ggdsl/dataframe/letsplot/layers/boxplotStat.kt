/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BoxplotStatContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.boxplot


public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
): Unit = boxplot(sourceX, sourceY.toDataSource(), varWidth, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
): Unit = boxplot(sourceX.toDataSource(), sourceY, varWidth, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
): Unit = boxplot(sourceX.toDataSource(), sourceY.toDataSource(), varWidth, block)
