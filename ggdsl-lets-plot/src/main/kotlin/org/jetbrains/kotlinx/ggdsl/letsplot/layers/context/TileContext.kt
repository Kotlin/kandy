package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.*


public class TileContext(parent: LayerCollectorContext)
    : LayerWithBorderLineContext(parent), WithX, WithY, WithAlpha, WithFillColor, WithWidth, WithHeight


