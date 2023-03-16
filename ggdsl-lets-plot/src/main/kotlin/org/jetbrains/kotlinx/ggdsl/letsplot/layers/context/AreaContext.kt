package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithAlpha
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithFillColor
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithX
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithY


public class AreaContext(parent: LayerCollectorContext)
    : LayerWithBorderLineContext(parent), WithX, WithY, WithAlpha, WithFillColor

