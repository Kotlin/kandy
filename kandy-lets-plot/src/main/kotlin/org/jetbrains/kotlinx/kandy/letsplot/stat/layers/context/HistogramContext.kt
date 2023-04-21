package org.jetbrains.kotlinx.kandy.letsplot.stat.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.BarsContext
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.BinStatContext

public class HistogramContext(parent: LayerCollectorContext) : BarsContext(parent), BinStatContext
