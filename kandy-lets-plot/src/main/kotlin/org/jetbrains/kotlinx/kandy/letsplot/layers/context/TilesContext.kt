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

/**
 * Interface for defining the necessary aesthetics and methods for tiles layers.
 *
 * Tile layers are typically used to create heatmaps or tile-based visualizations.
 * This interface provides the required aesthetics like `x`, `y`,
 * `alpha`, `fillColor`, `width`, and `height` for customization.
 *
 * The set of required aesthetics for tile layers includes `X` and `Y`.
 */
public interface TilesInterface : LayerContextInterface, WithBorderLine, WithX, WithY, WithAlpha,
    WithFillColor, WithWidth, WithHeight {

    /**
     * Gets the Geom object specific to **tiles** layers.
     *
     * @return the [Geom] object for **tiles**.
     */
    override val geom: Geom
        get() = TILE

    /**
     * Gets the set of required aesthetics for **tiles** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

/**
 * Context class for managing tile layers.
 *
 * This class serves as the context in which tile layers can be customized.
 * It inherits from [LayerWithBorderLineContext] and implements the [TilesInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class TilesContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), TilesInterface
