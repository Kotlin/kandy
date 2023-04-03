package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*


public class HLineContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithY, WithAlpha, WithColor, WithWidthAsSize, WithType
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.*

public interface HLineContextInterface: BindingContext {
    public val y: YInterceptAes get() = YInterceptAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

*/
/*@PlotDslMarker*//*

public class HLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), HLineContextInterface

*/
/*@PlotDslMarker*//*

public class HLineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), HLineContextInterface*/
