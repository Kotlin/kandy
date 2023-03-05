package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

/*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface CrossBarContextInterface: WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    //todo
    public val y: YDummyAes get() = YDummyAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)
    public val middle: MiddleAes get() = MiddleAes(this)

    public val fatten: FattenAes get() = FattenAes(this)

    public val width: WidthAes get() = WidthAes(this)
    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

*/
/*@PlotDslMarker*//*

public class CrossBarContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), CrossBarContextInterface

*/
/*@PlotDslMarker*//*

public class CrossBarContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), CrossBarContextInterface*/
