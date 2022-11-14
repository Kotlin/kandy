/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.layers

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContext
import org.jetbrains.kotlinx.ggdsl.letsplot.*
import org.jetbrains.kotlinx.ggdsl.letsplot.util.symbol.Symbol
import org.jetbrains.kotlinx.ggdsl.util.color.Color

@PublishedApi
internal val POINT_RANGE: LetsPlotGeom = LetsPlotGeom("pointrange")

public interface InnerPointSubContextInterface {
    public val parentContext: BindingContext
    public val symbol: ShapeAes get() = ShapeAes(parentContext)
    public val fillColor: FillAes get() = FillAes(parentContext)
    public val fatten: FattenAes get() = FattenAes(parentContext)
}

public interface InnerLineSubContextInterface {
    public val parentContext: BindingContext
    public val type: LineTypeAes get() = LineTypeAes(parentContext)
}

public interface PointRangeContext : BindingContext {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)
    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
    public val color: ColorAes get() = ColorAes(this)

    // todo separate????
    public val size: SizeAes get() = SizeAes(this)

    public val innerPoint: InnerPointSubContextInterface

    public val innerLine: InnerLineSubContextInterface
}

public inline fun LayerCollectorContext.pointRange(block: PointRangeContext.() -> Unit) {
    addLayer(PointRangeContext(this).apply(block), POINT_RANGE)
}
