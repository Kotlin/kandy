/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.Bin2DContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.bin2D
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins2D

public inline fun <reified T, reified R> PlotContext.bin2D(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
<<<<<<< HEAD
) = bin2D(sourceX.toColRef(), sourceY, bins, drop, block)
=======
): Unit = bin2D(sourceX.toDataSource(), sourceY, bins, drop, block)
>>>>>>> main

public inline fun <reified T, reified R> PlotContext.bin2D(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
<<<<<<< HEAD
) = bin2D(sourceX.toColRef(), sourceY.toColRef(), bins, drop, block)

inline fun <reified T, reified R: Any> PlotContext.bin2D(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
=======
): Unit = bin2D(sourceX.toDataSource(), sourceY.toDataSource(), bins, drop, block)

public inline fun <reified T, reified R> PlotContext.bin2D(
    sourceX: DataSource<T>,
>>>>>>> main
    sourceY: ColumnReference<R>,
    bins: Bins2D? = null,
    drop: Boolean? = null,
    block: Bin2DContext.() -> Unit
<<<<<<< HEAD
) = bin2D(sourceX, sourceY.toColRef(), bins, drop, block)


=======
): Unit = bin2D(sourceX, sourceY.toDataSource(), bins, drop, block)
>>>>>>> main
*/