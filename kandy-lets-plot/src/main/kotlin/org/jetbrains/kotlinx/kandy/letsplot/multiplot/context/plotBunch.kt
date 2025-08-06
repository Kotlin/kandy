/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.multiplot.context

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch

/**
 *  Context created by [org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch].
 */
public class PlotBunchBuffer {
    @PublishedApi
    internal val items: MutableList<PlotBunch.Item> = mutableListOf()

    /**
     *
     */
    public fun add(plot: Plot, x: Double, y: Double, width: Double, height: Double, dx: Int = 0, dy: Int = 0) {
        items.add(PlotBunch.Item(plot, x, y, width, height, dx, dy))
    }
}
