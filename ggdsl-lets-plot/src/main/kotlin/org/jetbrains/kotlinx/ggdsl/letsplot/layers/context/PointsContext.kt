package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.*

public class PointsContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithX, WithY, WithColor, WithSymbol, WithSize, WithAlpha
{
    /*
    // FILL SHAPES only
    public val borderWidth: StrokeAes  // TODO doesnt work lol
        get() = StrokeAes(this)
    public val fillColor: FillAes
        get() = FillAes(this)

     */
}
