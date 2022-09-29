package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.ViolinContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ViolinScale
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.violin
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel

inline fun <reified T : Any, reified R : Any> PlotContext.violin(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) = violin(
    sourceX.toColRef(),
    sourceY, drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

inline fun <reified T : Any> PlotContext.violin(
    sourceY: ColumnReference<T>,
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) = violin(
    sourceY.toColRef(), drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)


inline fun <reified T : Any, reified R : Any> PlotContext.violin(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
    sourceY: ColumnReference<R>,
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) = violin(
    sourceX,
    sourceY.toColRef(),
    drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

inline fun <reified T : Any, reified R : Any> PlotContext.violin(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
) = violin(
    sourceX.toColRef(),
    sourceY.toColRef(),
    drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

 */