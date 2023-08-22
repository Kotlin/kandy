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
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TILE

public interface TileInterface: LayerContextInterface, WithX, WithY, WithAlpha,
    WithFillColor, WithWidth, WithHeight {
    override val geom: Geom
        get() = TILE
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

public open class TileContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), TileInterface
