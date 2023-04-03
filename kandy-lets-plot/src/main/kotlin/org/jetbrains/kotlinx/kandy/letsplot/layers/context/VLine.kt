package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*


public class VLineContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithX, WithAlpha, WithColor, WithWidthAsSize, WithType
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.internal.*

public interface VLineContextInterface: BindingContext {
    public val x: XInterceptAes get() = XInterceptAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

*/
/*@PlotDslMarker*//*

public class VLineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), VLineContextInterface

*/
/*@PlotDslMarker*//*

public class VLineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), VLineContextInterface*/
