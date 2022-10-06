/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.BoxplotStatContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.boxplot


<<<<<<< HEAD
inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<T>,
    sourceY: ColumnReference<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
) = boxplot(sourceX, sourceY.toColRef(), varWidth, block)
=======
public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
): Unit = boxplot(sourceX, sourceY.toDataSource(), varWidth, block)
>>>>>>> main

public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
<<<<<<< HEAD
) = boxplot(sourceX.toColRef(), sourceY, varWidth, block)
=======
): Unit = boxplot(sourceX.toDataSource(), sourceY, varWidth, block)
>>>>>>> main

public inline fun <reified T : Any, reified R : Any> PlotContext.boxplot(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    varWidth: Boolean? = null,
    block: BoxplotStatContext<T>.() -> Unit
<<<<<<< HEAD
) = boxplot(sourceX.toColRef(), sourceY.toColRef(), varWidth, block)



=======
): Unit = boxplot(sourceX.toDataSource(), sourceY.toDataSource(), varWidth, block)
>>>>>>> main
*/