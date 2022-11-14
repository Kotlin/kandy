package org.jetbrains.kotlinx.ggdsl.letsplot.stat

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.SubLayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.SubLayerCollectorContextMutable

public abstract class StatLayerCollectorContextImmutable(parent: LayerCollectorContextImmutable)
    : SubLayerCollectorContextImmutable(parent)

public abstract class StatLayerCollectorContextMutable(parent: LayerCollectorContextMutable)
    : SubLayerCollectorContextMutable(parent)