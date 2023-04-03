package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*


public class TileContext(parent: LayerCollectorContext)
    : LayerWithBorderLineContext(parent), WithX, WithY, WithAlpha, WithFillColor, WithWidth, WithHeight


