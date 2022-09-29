package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.Density2DContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.density2D
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel

inline fun <reified T : Any, reified R: Any> PlotContext.density2D(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
) = density2D(
    sourceX.toColRef(), sourceY, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

inline fun <reified T : Any, reified R: Any> PlotContext.density2D(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
    sourceY: ColumnReference<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
) = density2D(
    sourceX, sourceY.toColRef(), kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

inline fun <reified T : Any, reified R: Any> PlotContext.density2D(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
) = density2D(
    sourceX.toColRef(),
    sourceY.toColRef(),
    kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

 */