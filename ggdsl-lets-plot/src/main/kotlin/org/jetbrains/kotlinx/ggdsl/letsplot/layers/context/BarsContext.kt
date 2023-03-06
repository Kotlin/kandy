package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.letsplot.layers.context.aes.*

public class BarsContext(parent: LayerCollectorContext)
    : LayerWithBorderLineContext(parent), WithX, WithY, WithAlpha, WithFillColor, WithWidth
/*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface BarContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

/*@PlotDslMarker*/
public open class BarContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), BarContextInterface

/*@PlotDslMarker*/
public open class BarContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), BarContextInterface

 */