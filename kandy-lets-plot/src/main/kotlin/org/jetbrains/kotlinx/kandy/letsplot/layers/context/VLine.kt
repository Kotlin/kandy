/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.V_LINE

public interface VLineInterface: LayerContextInterface, WithXIntercept, WithAlpha, WithColor,
    WithWidthAsSize, WithType, WithYFree {
    override val geom: Geom
        get() = V_LINE
    override val requiredAes: Set<Aes>
        get() = setOf(X_INTERCEPT)
}

public open class VLineContext(parent: LayerCollectorContext) : LayerContext(parent), VLineInterface {
    override val requiredAes: Set<Aes> = setOf(X_INTERCEPT)
    }
