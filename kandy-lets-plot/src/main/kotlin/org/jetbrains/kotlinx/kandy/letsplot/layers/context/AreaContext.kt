/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithAlpha
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithFillColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithX
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithY
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AREA

public interface AreaInterface : LayerContextInterface, WithX, WithY, WithAlpha,
    WithFillColor {
    override val geom: Geom get() = AREA
    override val requiredAes: Set<Aes> get() = setOf(X, Y)
}

public open class AreaContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), AreaInterface
