/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers

/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.ContourFilledContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.contourFilled
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Bins

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TY>,
    sourceZ: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
<<<<<<< HEAD
) = contourFilled(sourceX.toColRef(), sourceY, sourceZ, bins, block)
=======
): Unit = contourFilled(sourceX.toDataSource(), sourceY, sourceZ, bins, block)
>>>>>>> main

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
<<<<<<< HEAD
) = contourFilled(sourceX.toColRef(), sourceY.toColRef(), sourceZ, bins, block)
=======
): Unit = contourFilled(sourceX.toDataSource(), sourceY.toDataSource(), sourceZ, bins, block)
>>>>>>> main

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
<<<<<<< HEAD
) = contourFilled(sourceX.toColRef(), sourceY, sourceZ.toColRef(), bins, block)

inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TX>,
=======
): Unit = contourFilled(sourceX.toDataSource(), sourceY, sourceZ.toDataSource(), bins, block)

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
>>>>>>> main
    sourceY: ColumnReference<TY>,
    sourceZ: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
<<<<<<< HEAD
) = contourFilled(sourceX, sourceY.toColRef(), sourceZ, bins, block)

inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TX>,
=======
): Unit = contourFilled(sourceX, sourceY.toDataSource(), sourceZ, bins, block)

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
>>>>>>> main
    sourceY: ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
<<<<<<< HEAD
) = contourFilled(sourceX, sourceY.toColRef(), sourceZ.toColRef(), bins, block)

inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TX>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
) = contourFilled(sourceX, sourceY, sourceZ.toColRef(), bins, block)
=======
): Unit = contourFilled(sourceX, sourceY.toDataSource(), sourceZ.toDataSource(), bins, block)

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: DataSource<TX>,
    sourceY: DataSource<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
): Unit = contourFilled(sourceX, sourceY, sourceZ.toDataSource(), bins, block)
>>>>>>> main

public inline fun <reified TX, reified TY, reified TZ> PlotContext.contourFilled(
    sourceX: ColumnReference<TX>,
    sourceY: ColumnReference<TY>,
    sourceZ: ColumnReference<TZ>,
    bins: Bins? = null,
    block: ContourFilledContext.() -> Unit
<<<<<<< HEAD
) = contourFilled(sourceX.toColRef(), sourceY.toColRef(), sourceZ.toColRef(), bins, block)
=======
): Unit = contourFilled(sourceX.toDataSource(), sourceY.toDataSource(), sourceZ.toDataSource(), bins, block)
>>>>>>> main


 */
