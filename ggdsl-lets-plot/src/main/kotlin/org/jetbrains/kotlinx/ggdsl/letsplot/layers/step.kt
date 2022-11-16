/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.ggdsl.letsplot.*

@PublishedApi
internal val STEP: LetsPlotGeom = LetsPlotGeom("step")

public interface StepContextInterface : LayerContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: ColorAes get() = ColorAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val lineType: LineTypeAes get() = LineTypeAes(this)
    public val width: SizeAes get() = SizeAes(this)
}

@PlotDslMarker
public class StepContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), StepContextInterface

@PlotDslMarker
public class StepContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), StepContextInterface

public inline fun LayerCollectorContextImmutable.step(block: StepContextImmutable.() -> Unit) {
    addLayer(StepContextImmutable(this).apply(block), STEP)
}

public inline fun LayerCollectorContextMutable.step(block: StepContextMutable.() -> Unit) {
    addLayer(StepContextMutable(this).apply(block), STEP)
}
