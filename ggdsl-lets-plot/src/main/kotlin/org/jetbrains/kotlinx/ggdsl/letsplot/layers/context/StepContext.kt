package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.*

public class StepContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithX, WithY, WithAlpha, WithColor, WithWidthAsSize, WithLineType
