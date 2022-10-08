package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContextInterface
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.SubLayerCollectorContext

public abstract class StatContext(parent: LayerCollectorContextInterface) : SubLayerCollectorContext(parent)