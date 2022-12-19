package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface PointContextInterface : BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val symbol: ShapeAes get() = ShapeAes(this)

    public val size: SizeAes get() = SizeAes(this)
    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)

    // FILL SHAPES only
    public val borderWidth: StrokeAes  // TODO doesnt work lol
        get() = StrokeAes(this)
    public val fillColor: FillAes
        get() = FillAes(this)
}

@PlotDslMarker
public class PointContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent), PointContextInterface

@PlotDslMarker
public class PointMutableMutableContextMutable(parent: LayerCollectorContextMutable) :
    LayerContextMutable(parent), PointContextInterface