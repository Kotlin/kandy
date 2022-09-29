package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.ContourFilledContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.contourFilled
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TY>,
    sourceZ: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toColRef(), sourceY, sourceZ, bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toColRef(), sourceY.toColRef(), sourceZ, bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toColRef(), sourceY, sourceZ.toColRef(), bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY.toColRef(), sourceZ, bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY.toColRef(), sourceZ.toColRef(), bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TX>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY, sourceZ.toColRef(), bins, block)

inline fun <reified TX : Any, reified TY : Any, reified TZ : Any> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX.toColRef(), sourceY.toColRef(), sourceZ.toColRef(), bins, block)


 */
