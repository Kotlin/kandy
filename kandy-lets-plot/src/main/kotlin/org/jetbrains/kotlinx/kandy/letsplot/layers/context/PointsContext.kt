/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

// todo add Stroke
public class PointsContext(parent: LayerCollectorContext) : LayerContext(parent), WithX, WithY, WithColor, WithSymbol,
    WithSize, WithAlpha, WithFillColor {
    override val requiredAes: Set<AesName> = setOf(X, Y)
}
