/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*

import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.QQLineContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.qqLine
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Distribution

public inline fun <reified T> PlotContext.qqLine(
    source: ColumnReference<T>,
    distribution: Distribution? = null,
    quantiles: Pair<Double, Double>? = null,
    block: QQLineContext.() -> Unit
<<<<<<< HEAD
) = qqLine(source.toColRef(), distribution, quantiles, block)


=======
): Unit = qqLine(source.toDataSource(), distribution, quantiles, block)
>>>>>>> main
*/