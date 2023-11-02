/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RECT

public interface RectanglesInterface: LayerContextInterface, WithBorderLine, WithXMin, WithXMax,
    WithYMin, WithYMax,
    WithFillColor, WithAlpha, WithXFree, WithYFree {
    override val geom: Geom
        get() = RECT
    override val requiredAes: Set<Aes>
        get() = setOf(X_MIN, X_MAX, Y_MIN, Y_MAX)
}

public open class RectanglesContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), RectanglesInterface
