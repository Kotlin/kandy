/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker


public class LineRangeContext(parent: LayerCollectorContext)
    :LayerWithBorderLineContext(parent), WithX, WithYMin, WithYMax, WithAlpha, WithYFree

/*
public interface LineRangeContextInterface : WithBorderLineContextInterface {
    public val x: XAes get() = XAes(this)

    public val yMin: YMinAes get() = YMinAes(this)
    public val yMax: YMaxAes get() = YMaxAes(this)

    public val alpha: AlphaAes get() = AlphaAes(this)
}

*/
/*@PlotDslMarker*//*

public class LineRangeContextImmutable(parent: LayerCollectorContextImmutable)
    : LayerWithBorderLineContextImmutable(parent), LineRangeContextInterface

*/
/*@PlotDslMarker*//*

public class LineRangeContextMutable(parent: LayerCollectorContextMutable):
    LayerWithBorderLineContextMutable(parent), LineRangeContextInterface*/
