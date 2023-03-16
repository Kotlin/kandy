package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithAlpha
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithX
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithYMax
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.WithYMin

// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker


public class LineRangeContext(parent: LayerCollectorContext)
    :LayerWithBorderLineContext(parent), WithX, WithYMin, WithYMax, WithAlpha

/*
public interface LineRangeContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
}

*/
/*@PlotDslMarker*//*

public class LineRangeContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), LineRangeContextInterface

*/
/*@PlotDslMarker*//*

public class LineRangeContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), LineRangeContextInterface*/
