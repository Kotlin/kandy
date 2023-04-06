/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class PointsContext(parent: LayerCollectorContext) : LayerContext(parent), WithX, WithY, WithColor, WithSymbol,
    WithSize, WithAlpha, WithFillColor {
    /*
    // FILL SHAPES only
    public val borderWidth: StrokeAes  // TODO doesnt work lol
        get() = StrokeAes(this)
     */
}
