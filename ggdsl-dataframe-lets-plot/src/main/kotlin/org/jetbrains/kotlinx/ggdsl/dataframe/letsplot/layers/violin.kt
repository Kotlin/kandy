/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.ViolinContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.ViolinScale
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.violin
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel

public inline fun <reified T, reified R> PlotContext.violin(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<R>,
    drawQuantiles: List<Double>? = null,
    scale: ViolinScale? = null,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: ViolinContext.() -> Unit,
<<<<<<< HEAD
) = violin(
    sourceX.toColRef(),
=======
): Unit = violin(
    sourceX.toDataSource(),
>>>>>>> main
    sourceY, drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

public inline fun <reified T> PlotContext.violin(
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
<<<<<<< HEAD
) = violin(
    sourceY.toColRef(), drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)


inline fun <reified T, reified R> PlotContext.violin(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<T>,
=======
): Unit = violin(
    sourceY.toDataSource(), drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)


public inline fun <reified T, reified R> PlotContext.violin(
    sourceX: DataSource<T>,
>>>>>>> main
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
): Unit = violin(
    sourceX,
    sourceY.toColRef(),
    drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

public inline fun <reified T, reified R> PlotContext.violin(
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
<<<<<<< HEAD
) = violin(
    sourceX.toColRef(),
    sourceY.toColRef(),
=======
): Unit = violin(
    sourceX.toDataSource(),
    sourceY.toDataSource(),
>>>>>>> main
    drawQuantiles, scale, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

 */