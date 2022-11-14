/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.*

public class PointsContextImmutable(parent: LayerCollectorContextImmutable) : LayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)


    public val size: SizeAes = SizeAes(this)
    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val borderWidth: BorderWidthAes = BorderWidthAes(this)
    public val borderColor: BorderColorAes = BorderColorAes(this)

    public val symbol: SymbolAes = SymbolAes(this)
}


public class LineContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)

    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthAes = WidthAes(this)

    public val lineType: LineTypeAes = LineTypeAes(this)
}


public class BarsContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent) {
    public val x: XAes = XAes(this)
    public val y: YAes = YAes(this)


    public val color: ColorAes = ColorAes(this)
    public val alpha: AlphaAes = AlphaAes(this)

    public val width: WidthAes = WidthAes(this)

    public val borderWidth: BorderWidthAes = BorderWidthAes(this)
    public val borderColor: BorderColorAes = BorderColorAes(this)
}