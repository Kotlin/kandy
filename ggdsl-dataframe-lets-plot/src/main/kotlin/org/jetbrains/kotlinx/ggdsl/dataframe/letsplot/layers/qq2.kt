/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.QQ2Context
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.qq2

public inline fun <reified T, reified R> PlotContext.qq2(
    sourceX: ColumnReference<T>,
    sourceY: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<R>,
    block: QQ2Context.() -> Unit
<<<<<<< HEAD
) = qq2(sourceX.toColRef(), sourceY, block)

inline fun <reified T, reified R: Any> PlotContext.qq2(
    sourceX: org.jetbrains.kotlinx.ggdsl.ir.data.ColumnReference<T>,
    sourceY: ColumnReference<R>,
    block: QQ2Context.() -> Unit
) = qq2(sourceX, sourceY.toColRef(), block)
=======
): Unit = qq2(sourceX.toDataSource(), sourceY, block)

public inline fun <reified T, reified R> PlotContext.qq2(
    sourceX: DataSource<T>,
    sourceY: ColumnReference<R>,
    block: QQ2Context.() -> Unit
): Unit = qq2(sourceX, sourceY.toDataSource(), block)
>>>>>>> main

public inline fun <reified T, reified R> PlotContext.qq2(
    sourceX: ColumnReference<T>,
    sourceY: ColumnReference<R>,
    block: QQ2Context.() -> Unit
<<<<<<< HEAD
) = qq2(sourceX.toColRef(), sourceY.toColRef(), block)


=======
): Unit = qq2(sourceX.toDataSource(), sourceY.toDataSource(), block)
>>>>>>> main
*/