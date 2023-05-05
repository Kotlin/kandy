/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithAlpha
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithFillColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithX
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithY

public class RasterContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), WithX, WithY,
    WithFillColor, WithAlpha {
    override val requiredAes: Set<AesName> = setOf(X, Y)
    }

/*
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.letsplot.internal.AlphaAes
import org.jetbrains.kotlinx.kandy.letsplot.internal.FillAes
import org.jetbrains.kotlinx.kandy.letsplot.internal.XAes
import org.jetbrains.kotlinx.kandy.letsplot.internal.YAes

public interface RasterContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
}

*/
/*@PlotDslMarker*//*

public class RasterContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), RasterContextInterface

*/
/*@PlotDslMarker*//*

public class RasterContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), RasterContextInterface*/
