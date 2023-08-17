/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.STEP

public interface StepInterface: LayerContextInterface, WithX, WithY, WithAlpha, WithColor,
    WithWidthAsSize, WithLineType {
    override val geom: Geom
        get() = STEP
    override val requiredAes: Set<AesName>
        get() = setOf(X, Y)
}


public open class StepContext(parent: LayerCollectorContext) : LayerContext(parent), StepInterface
