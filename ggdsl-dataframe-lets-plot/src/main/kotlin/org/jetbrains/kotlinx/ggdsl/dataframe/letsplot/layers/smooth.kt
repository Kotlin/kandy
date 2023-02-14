/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.SmoothContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.smooth
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.SmoothMethod

public inline fun <reified T, reified R> PlotContext.smooth(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
<<<<<<< HEAD
) = smooth(sourceX.toColRef(), sourceY, method, pointsNumber, se, level, block)

inline fun <reified T, reified R> PlotContext.smooth(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<T>,
=======
): Unit = smooth(sourceX.toDataSource(), sourceY, method, pointsNumber, se, level, block)

public inline fun <reified T, reified R> PlotContext.smooth(
    sourceX: DataSource<T>,
>>>>>>> main
    sourceY: ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
<<<<<<< HEAD
) = smooth(sourceX, sourceY.toColRef(), method, pointsNumber, se, level, block)
=======
): Unit = smooth(sourceX, sourceY.toDataSource(), method, pointsNumber, se, level, block)
>>>>>>> main

public inline fun <reified T, reified R> PlotContext.smooth(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    method: SmoothMethod? = null,
    pointsNumber: Int? = null,
    se: Boolean? = null,
    level: Double? = null,
    block: SmoothContext.() -> Unit
<<<<<<< HEAD
) = smooth(sourceX.toColRef(), sourceY.toColRef(), method, pointsNumber, se, level, block)



=======
): Unit = smooth(sourceX.toDataSource(), sourceY.toDataSource(), method, pointsNumber, se, level, block)
>>>>>>> main
*/