package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toDataSource
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.Density2DContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.density2D
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel

public inline fun <reified T : Any, reified R: Any> PlotContext.density2D(
    sourceX: ColumnReference<T>,
    sourceY: DataSource<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
): Unit = density2D(
    sourceX.toDataSource(), sourceY, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

public inline fun <reified T : Any, reified R: Any> PlotContext.density2D(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
): Unit = density2D(
    sourceX, sourceY.toDataSource(), kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

public inline fun <reified T : Any, reified R: Any> PlotContext.density2D(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DContext.() -> Unit
): Unit = density2D(
    sourceX.toDataSource(),
    sourceY.toDataSource(),
    kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)