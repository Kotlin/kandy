/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*


public class ErrorBarContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), WithX, WithYMin,
    WithYMax, WithWidth, WithAlpha, WithYFree {
    override val requiredAes: Set<AesName> = setOf(X, Y_MIN, Y_MAX)
    }
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.letsplot.internal.*

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
