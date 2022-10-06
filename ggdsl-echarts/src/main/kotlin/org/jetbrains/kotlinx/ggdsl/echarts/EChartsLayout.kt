/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.ir.Layout

/**
 * Plot layout settings
 *
 * @param title the title of this plot
 * @param size the size of this plot
 */
public data class EChartsLayout(
    var title: String? = null,
    // todo width height?
    var size: Pair<Int, Int>? = null,
) : Layout

/*
public inline fun PlotContext.layout(block: EChartsLayout.() -> Unit) {
    layoutAccessor = EChartsLayout().apply(block)
}

 */
