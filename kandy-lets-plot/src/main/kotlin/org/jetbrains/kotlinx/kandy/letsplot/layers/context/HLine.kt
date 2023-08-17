/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.H_LINE

public interface HLineInterface: LayerContextInterface, WithYIntercept, WithAlpha, WithColor,
    WithWidthAsSize, WithType, WithXFree {
    override val requiredAes: Set<AesName>
        get() = setOf(Y_INTERCEPT)
    override val geom: Geom
        get() = H_LINE
}

public open class HLineContext(parent: LayerCollectorContext) : LayerContext(parent), HLineInterface
