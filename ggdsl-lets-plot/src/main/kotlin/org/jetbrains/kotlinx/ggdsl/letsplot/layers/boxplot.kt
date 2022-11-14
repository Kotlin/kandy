/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextMutable
import org.jetbrains.kotlinx.ggdsl.letsplot.*


public val BOXPLOT: LetsPlotGeom = LetsPlotGeom("boxplot")

public interface BoxplotContextInterface: BindingContext {
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
    LayerWithBorderLineContextMutable(parent), BoxplotContextInterface {
}

public inline fun LayerCollectorContextImmutable.boxplot(block: BoxplotContextImmutable.() -> Unit) {
    addLayer(BoxplotContextImmutable(this).apply(block), BOXPLOT)
}

public inline fun LayerCollectorContextMutable.boxplot(block: BoxplotContextMutable.() -> Unit) {
    addLayer(BoxplotContextMutable(this).apply(block), BOXPLOT)
}
