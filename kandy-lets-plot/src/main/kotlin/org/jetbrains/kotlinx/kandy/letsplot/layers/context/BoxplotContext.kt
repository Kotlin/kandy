/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BOXPLOT

public interface BoxplotInterface : LayerContextInterface, WithX, WithAlpha,
    WithFillColor, WithWidth,
    WithLower, WithUpper, WithMiddle, WithYMin, WithYMax, WithFatten, WithYFree {
    override val geom: Geom
        get() = BOXPLOT
    override val requiredAes: Set<Aes> get() = setOf(X, LOWER, UPPER, MIDDLE, Y_MIN, Y_MAX)
}

public open class BoxplotContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), BoxplotInterface
