/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val LINE_RANGE: LetsPlotGeom = LetsPlotGeom("linerange")

public interface LineRangeContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)


    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)

}

@PlotDslMarker
public class LineRangeContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), LineRangeContextInterface

@PlotDslMarker
public class LineRangeContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), LineRangeContextInterface

public inline fun LayerCollectorContextImmutable.lineRange(block: LineRangeContextImmutable.() -> Unit) {
    addLayer(LineRangeContextImmutable(this).apply(block), LINE_RANGE)
}

public inline fun LayerCollectorContextMutable.lineRange(block: LineRangeContextMutable.() -> Unit) {
    addLayer(LineRangeContextMutable(this).apply(block), LINE_RANGE)
}