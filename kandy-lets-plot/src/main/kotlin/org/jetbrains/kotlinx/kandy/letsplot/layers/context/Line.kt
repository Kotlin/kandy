package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class LineContext(parent: LayerCollectorContext)
    :LayerContext(parent), WithX, WithY, WithAlpha, WithColor, WithWidthAsSize, WithType

/*
public interface LineContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

*/
/*@PlotDslMarker*//*

public class LineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), LineContextInterface

*/
/*@PlotDslMarker*//*

public class LineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), LineContextInterface*/
