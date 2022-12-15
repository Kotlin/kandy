package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.*
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface LineContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val type: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

@PlotDslMarker
public class LineContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerContextImmutable(parent), LineContextInterface

@PlotDslMarker
public class LineContextMutable(parent: LayerCollectorContextMutable):
    LayerContextMutable(parent), LineContextInterface