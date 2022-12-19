package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface BoxplotContextInterface: WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)

    public val lower: LowerAes get() = LowerAes(this)
    public val upper: UpperAes get() = UpperAes(this)
    public val middle: MiddleAes get() = MiddleAes(this)
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val fatten: FattenAes get() = FattenAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

@PlotDslMarker
public class BoxplotContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), BoxplotContextInterface

@PlotDslMarker
public class BoxplotContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), BoxplotContextInterface