package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class SegmentContext(parent: LayerCollectorContext)
    : LayerContext(parent), WithColor, WithAlpha, WithLineType, WithWidthAsSize,
    WithXBegin, WithYBegin, WithXEnd, WithYEnd

/*
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.letsplot.internal.*

public interface SegmentContextInterface : LayerContext {
    public val xDummyAes: XDummyAes get() = XDummyAes(this)
    public val yDummyAes: YDummyAes get() = YDummyAes(this)

    public val xBegin: XAes get() = XAes(this)
    public val yBegin: YAes get() = YAes(this)
    public val xEnd: XEndAes get() = XEndAes(this)
    public val yEnd: YEndAes get() = YEndAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val lineType: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)

    // todo speed and flow
}

*/
/*@PlotDslMarker*//*

public class SegmentContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), SegmentContextInterface

*/
/*@PlotDslMarker*//*

public class SegmentContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), SegmentContextInterface*/
