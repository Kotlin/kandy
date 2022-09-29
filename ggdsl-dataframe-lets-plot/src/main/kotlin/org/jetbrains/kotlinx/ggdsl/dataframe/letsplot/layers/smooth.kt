package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.SmoothContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.smooth
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.SmoothMethod

inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
) = smooth(sourceX.toColRef(), sourceY, method, pointsNumber, se, level, block)

inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
    sourceY: ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
) = smooth(sourceX, sourceY.toColRef(), method, pointsNumber, se, level, block)

inline fun <reified T : Any, reified R : Any> PlotContext.smooth(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
) = smooth(sourceX.toColRef(), sourceY.toColRef(), method, pointsNumber, se, level, block)


 */