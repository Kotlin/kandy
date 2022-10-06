/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.Density2DFilledContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.density2DFilled
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.BandWidth
import org.jetbrains.kotlinx.ggdsl.letsplot.util.statParameters.Kernel

public inline fun <reified T : Any, reified R : Any> PlotContext.density2DFilled(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DFilledContext.() -> Unit
<<<<<<< HEAD
) = density2DFilled(
    sourceX.toColRef(), sourceY, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

inline fun <reified T : Any, reified R: Any> PlotContext.density2DFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
=======
): Unit = density2DFilled(
    sourceX.toDataSource(), sourceY, kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

public inline fun <reified T : Any, reified R : Any> PlotContext.density2DFilled(
    sourceX: DataSource<T>,
>>>>>>> main
    sourceY: ColumnReference<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DFilledContext.() -> Unit
<<<<<<< HEAD
) = density2DFilled(
    sourceX, sourceY.toColRef(), kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
=======
): Unit = density2DFilled(
    sourceX, sourceY.toDataSource(), kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
>>>>>>> main
)

public inline fun <reified T : Any, reified R : Any> PlotContext.density2DFilled(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    kernel: Kernel? = null,
    bandWidth: BandWidth? = null,
    pointsSampled: Int? = null,
    trim: Boolean? = null,
    adjust: Double? = null,
    fullScanMax: Int? = null,
    block: Density2DFilledContext.() -> Unit
<<<<<<< HEAD
) = density2DFilled(
    sourceX.toColRef(),
    sourceY.toColRef(),
=======
): Unit = density2DFilled(
    sourceX.toDataSource(),
    sourceY.toDataSource(),
>>>>>>> main
    kernel, bandWidth, pointsSampled, trim, adjust, fullScanMax, block
)

 */