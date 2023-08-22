/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.ERROR_BAR

public interface ErrorBarInterface : LayerContextInterface, WithX, WithYMin,
    WithYMax, WithWidth, WithAlpha, WithYFree {
    override val geom: Geom
        get() = ERROR_BAR
    override val requiredAes: Set<Aes>
        get ()= setOf(X, Y_MIN, Y_MAX)
}

public open class ErrorBarContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), ErrorBarInterface
