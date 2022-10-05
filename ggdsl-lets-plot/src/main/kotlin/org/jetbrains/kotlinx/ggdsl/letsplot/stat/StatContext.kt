package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.SubLayerCollectorContext

abstract class StatContext(parent: LayerCollectorContext) : SubLayerCollectorContext(parent)