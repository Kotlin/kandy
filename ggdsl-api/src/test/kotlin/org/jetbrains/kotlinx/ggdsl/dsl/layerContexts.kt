/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextImmutable


class PointsContextImmutable(parent: LayerCollectorContextImmutable) : LayerContextImmutable(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val size = SizeAes(this)
    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)

    val symbol = SymbolAes(this)
}

class LineContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val lineType = LineTypeAes(this)
}

class BarsContextImmutable(parent: LayerCollectorContextImmutable) :
    LayerContextImmutable(parent) {
    val x = XAes(this)
    val y = YAes(this)

    val color = ColorAes(this)
    val alpha = AlphaAes(this)

    val width = WidthAes(this)

    val borderWidth = BorderWidthAes(this)
    val borderColor = BorderColorAes(this)
}