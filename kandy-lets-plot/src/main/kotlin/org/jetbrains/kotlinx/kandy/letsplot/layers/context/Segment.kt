/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.SEGMENT

public interface SegmentInterface: LayerContextInterface, WithColor, WithAlpha, WithLineType,
    WithWidthAsSize,
    WithXBegin, WithYBegin, WithXEnd, WithYEnd, WithXFree, WithYFree {
    override val geom: Geom
        get() = SEGMENT
    override val requiredAes: Set<AesName>
        get() = setOf(X_BEGIN, Y_BEGIN, X_END, Y_END)
}

public open class SegmentContext(parent: LayerCollectorContext) : LayerContext(parent), SegmentInterface
