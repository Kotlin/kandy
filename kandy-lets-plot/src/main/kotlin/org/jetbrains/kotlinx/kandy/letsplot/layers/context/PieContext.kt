/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.PIE

public interface PieInterface: LayerContextInterface, WithX, WithY, WithSlice, WithExplode, WithHole, WithSize, WithAlpha, WithFillColor,
    WithStroke, WithStrokeColor {
    override val geom: Geom
        get() = PIE
    override val requiredAes: Set<AesName>  // todo
        get() = setOf()
}

public open class PieContext(parent: LayerCollectorContext) : LayerContext(parent), PieInterface
