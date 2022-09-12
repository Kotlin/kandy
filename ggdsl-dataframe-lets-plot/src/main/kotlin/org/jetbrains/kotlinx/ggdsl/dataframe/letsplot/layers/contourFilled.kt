/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers


import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.ContourFilledContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.contourFilled
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: DataSource<TY>,
    sourceZ: DataSource<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toDataSource(), sourceY, sourceZ, bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: DataSource<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toDataSource(), sourceY.toDataSource(), sourceZ, bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: DataSource<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toDataSource(), sourceY, sourceZ.toDataSource(), bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: DataSource<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY.toDataSource(), sourceZ, bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY.toDataSource(), sourceZ.toDataSource(), bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
    sourceY: DataSource<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY, sourceZ.toDataSource(), bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toDataSource(), sourceY.toDataSource(), sourceZ.toDataSource(), bins, block)

