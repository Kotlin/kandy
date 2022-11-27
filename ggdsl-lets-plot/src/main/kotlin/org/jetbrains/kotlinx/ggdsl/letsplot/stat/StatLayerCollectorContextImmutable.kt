package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextInterface
import org.jetbrains.kotlinx.ggdsl.ir.Layer

public abstract class StatLayerCollectorContext(parent: LayerCollectorContextInterface)
    : LayerCollectorContextImmutable {
    override val layers: MutableList<Layer> = parent.layers
}


/*
public abstract class StatLayerCollectorContextImmutable(parent: LayerCollectorContextImmutable)
    : StatLayerCollectorContext(parent), LayerCollectorContextImmutable

public abstract class StatLayerCollectorContextMutable(parent: LayerCollectorContextMutable)
    : StatLayerCollectorContext(parent), LayerCollectorContextMutable



 */

