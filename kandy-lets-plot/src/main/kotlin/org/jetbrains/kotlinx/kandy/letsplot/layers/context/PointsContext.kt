package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class PointsContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithX, WithY, WithColor, WithSymbol, WithSize, WithAlpha, WithFillColor
{
    /*
    // FILL SHAPES only
    public val borderWidth: StrokeAes  // TODO doesnt work lol
        get() = StrokeAes(this)
     */
}
