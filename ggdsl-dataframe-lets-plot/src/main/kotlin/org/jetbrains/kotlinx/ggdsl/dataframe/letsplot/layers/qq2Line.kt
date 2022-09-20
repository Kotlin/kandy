/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.QQ2LineContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.qq2Line

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2Line(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
): Unit = qq2Line(sourceX.toDataSource(), sourceY, quantiles, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2Line(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
): Unit = qq2Line(sourceX, sourceY.toDataSource(), quantiles, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2Line(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
): Unit = qq2Line(sourceX.toDataSource(), sourceY.toDataSource(), quantiles, block)
