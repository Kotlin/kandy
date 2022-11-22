package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextInterface
import org.jetbrains.kotlinx.ggdsl.ir.Layer

public abstract class StatLayerCollectorContext(parent: LayerCollectorContextInterface): LayerCollectorContextInterface {
    override val layers: MutableList<Layer> = parent.layers
}
/*
public abstract class StatLayerCollectorContextImmutable(parent: LayerCollectorContextImmutable)
    : SubLayerCollectorContextImmutable(parent)

public abstract class StatLayerCollectorContextMutable(parent: LayerCollectorContextMutable)
    : SubLayerCollectorContextMutable(parent)

 */

