/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*

// todo add Stroke
public class PieContext(parent: LayerCollectorContext) : LayerContext(parent),
    WithX, WithY, WithSlice, WithExplode, WithHole, WithSize, WithAlpha, WithFillColor,
        WithStroke, WithStrokeColor {
    override val requiredAes: Set<AesName> = setOf() // todo
}
