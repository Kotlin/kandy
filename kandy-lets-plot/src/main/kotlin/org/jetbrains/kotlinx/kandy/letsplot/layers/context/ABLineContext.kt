/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLOPE
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AB_LINE

public interface ABLineInterface : LayerContextInterface, WithType,
    WithColor, WithAlpha,
    WithWidthAsSize, WithSlope, WithIntercept,
    WithXFree, WithYFree {
    override val geom: Geom
        get() = AB_LINE
    override val requiredAes: Set<AesName>
        get() = setOf(SLOPE, INTERCEPT)
}

public open class ABLineContext(parent: LayerCollectorContext) : LayerContext(parent), ABLineInterface
