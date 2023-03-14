package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.AlphaAes
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.XAes
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.YMaxAes
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.YMinAes

public interface LineRangeContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
}

/*@PlotDslMarker*/
public class LineRangeContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), LineRangeContextInterface

/*@PlotDslMarker*/
public class LineRangeContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), LineRangeContextInterface