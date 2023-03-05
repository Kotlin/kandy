package org.jetbrains.kotlinx.ggdsl.letsplot.layers.context

/*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.*

public interface ErrorBarContextInterface: BindingContext {
    public val x: XAes get() = XAes(this)

    //todo
    public val y: YDummyAes get() = YDummyAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

*/
/*@PlotDslMarker*//*

public class ErrorBarContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), ErrorBarContextInterface

*/
/*@PlotDslMarker*//*

public class ErrorBarContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), ErrorBarContextInterface*/
