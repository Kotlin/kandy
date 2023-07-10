/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public interface BarsContextInterface : LayerContextInterface, WithBorderLineContext, WithX, WithY,
    WithAlpha, WithFillColor, WithWidth {
    public override val requiredAes: Set<AesName>
        get() = setOf(X, Y)
}

public open class BarsContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent),
    BarsContextInterface {
    override val requiredAes: Set<AesName> = setOf(X, Y)
}
/*
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContextMutable
// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.letsplot.internal.*

public interface BarContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)
    public val y: YAes get() = YAes(this)

    public val color: FillAes get() = FillAes(this)
    public val alpha: AlphaAes get() = AlphaAes(this)
    public val width: WidthAes get() = WidthAes(this)
}

/*@PlotDslMarker*/
public open class BarContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerWithBorderLineContextImmutable(parent), BarContextInterface

/*@PlotDslMarker*/
public open class BarContextMutable(parent: LayerCollectorContextMutable)
    : LayerWithBorderLineContextMutable(parent), BarContextInterface

 */