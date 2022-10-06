/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.layers
/*
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.ggdsl.dataframe.toColRef
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.QQContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.qq
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.stat.Distribution

public inline fun <reified T : Any> PlotContext.qq(
    source: ColumnReference<T>,
    distribution: Distribution? = null,
    block: QQContext.() -> Unit
<<<<<<< HEAD
) = qq(source.toColRef(), distribution, block)



=======
): Unit = qq(source.toDataSource(), distribution, block)
>>>>>>> main
*/