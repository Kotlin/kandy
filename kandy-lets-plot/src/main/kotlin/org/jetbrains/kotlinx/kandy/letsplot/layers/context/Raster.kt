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
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithAlpha
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithFillColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithX
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.WithY
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RASTER

/**
 * Interface defining the necessary aesthetics and methods for raster layers.
 *
 * Raster layers are used to represent data in a grid format,
 * commonly used for heatmaps or geographic information systems.
 * The interface provides aesthetics such as `x`, `y`, `fillColor`,
 * and `alpha` for a complete customization of raster representation.
 *
 * Required aesthetics for raster layers are `x` and `y`.
 */
public interface RasterInterface : LayerContextInterface, WithX, WithY,
    WithFillColor, WithAlpha {

    /**
     * Gets the Geom object specific to **raster** layers.
     *
     * @return the [Geom] object for **raster**.
     */
    override val geom: Geom
        get() = RASTER

    /**
     * Gets the set of required aesthetics for **raster** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

/**
 * Context class for managing raster layers.
 *
 * This class provides the context in which raster layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [RasterInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class RasterContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), RasterInterface
