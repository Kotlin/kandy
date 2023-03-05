package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

/*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface RibbonContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YDummyAes get() = YDummyAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

*/
/*@PlotDslMarker*//*

public class RibbonContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), RibbonContextInterface

*/
/*@PlotDslMarker*//*

public class RibbonContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), RibbonContextInterface*/
