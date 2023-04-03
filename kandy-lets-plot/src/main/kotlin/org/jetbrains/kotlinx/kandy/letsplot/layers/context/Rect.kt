package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*


public class RectContext(parent: LayerCollectorContext)
    : LayerWithBorderLineContext(parent), WithXMin, WithXMax, WithYMin, WithYMax,
    WithFillColor, WithAlpha

/*
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.letsplot.internal.*

public interface RectContextInterface : WithBorderLineContextInterface {
    public val y: YDummyAes get() = YDummyAes(this)

    public val xMin: XMinAes get() = XMinAes(this)
    public val xMax: XMaxAes get() = XMaxAes(this)
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

*/
/*@PlotDslMarker*//*

public class RectContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), RectContextInterface

*/
/*@PlotDslMarker*//*

public class RectContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), RectContextInterface*/
