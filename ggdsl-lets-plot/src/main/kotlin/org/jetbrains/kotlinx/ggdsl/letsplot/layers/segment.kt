/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val SEGMENT: LetsPlotGeom = LetsPlotGeom("segment")

public interface SegmentContextInterface : LayerContextInterface {
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

@PlotDslMarker
public class SegmentContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), SegmentContextInterface

@PlotDslMarker
public class SegmentContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), SegmentContextInterface

public inline fun LayerCollectorContextImmutable.segment(block: SegmentContextImmutable.() -> Unit) {
    addLayer(SegmentContextImmutable(this).apply(block), SEGMENT)
}

public inline fun LayerCollectorContextMutable.segment(block: SegmentContextMutable.() -> Unit) {
    addLayer(SegmentContextMutable(this).apply(block), SEGMENT)
}
