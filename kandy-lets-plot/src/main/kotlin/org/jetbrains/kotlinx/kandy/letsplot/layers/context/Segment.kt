/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

public class SegmentContext(parent: LayerCollectorContext) : LayerContext(parent), WithColor, WithAlpha, WithLineType,
    WithWidthAsSize,
    WithXBegin, WithYBegin, WithXEnd, WithYEnd, WithXFree, WithYFree {
    override val requiredAes: Set<AesName> = setOf(X_BEGIN, Y_BEGIN, X_END, Y_END)
    }

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
