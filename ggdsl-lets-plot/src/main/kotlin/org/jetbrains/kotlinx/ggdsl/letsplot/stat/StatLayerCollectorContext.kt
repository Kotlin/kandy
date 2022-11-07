package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContextInterface
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.SubLayerCollectorContext

public abstract class StatLayerCollectorContext(parent: LayerCollectorContextInterface)
    : SubLayerCollectorContext(parent)