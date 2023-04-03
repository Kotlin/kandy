package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class PathContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithX, WithY, WithAlpha, WithColor, WithWidthAsSize, WithLineType
