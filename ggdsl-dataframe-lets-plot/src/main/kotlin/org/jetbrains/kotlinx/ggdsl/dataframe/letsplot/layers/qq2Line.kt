/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.QQ2LineContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.qq2Line

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2Line(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
<<<<<<< HEAD
) = qq2Line(sourceX.toColRef(), sourceY, quantiles, block)

inline fun <reified T : Any, reified R: Any> PlotContext.qq2Line(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
    sourceY: ColumnReference<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
) = qq2Line(sourceX, sourceY.toColRef(), quantiles, block)
=======
): Unit = qq2Line(sourceX.toDataSource(), sourceY, quantiles, block)

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2Line(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
): Unit = qq2Line(sourceX, sourceY.toDataSource(), quantiles, block)
>>>>>>> main

public inline fun <reified T : Any, reified R : Any> PlotContext.qq2Line(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    quantiles: Pair<Double, Double>? = null,
    block: QQ2LineContext.() -> Unit
<<<<<<< HEAD
) = qq2Line(sourceX.toColRef(), sourceY.toColRef(), quantiles, block)


=======
): Unit = qq2Line(sourceX.toDataSource(), sourceY.toDataSource(), quantiles, block)
>>>>>>> main
*/