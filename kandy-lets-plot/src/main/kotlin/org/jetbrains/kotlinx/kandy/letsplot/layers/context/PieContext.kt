/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.PIE

/**
 * Interface defining the necessary aesthetics and methods for Pie layers.
 *
 * Pie layers are used to create pie charts, which are often used to represent categorical data.
 * The interface provides aesthetics such as `x`, `y`, `slice`, `explode`, `hole`, `size`,
 * `alpha`, `fillColor`, `stroke`, and `strokeColor` for full customization of the pie chart.
 */
public interface PieInterface : LayerContextInterface, WithX, WithY, WithSlice, WithExplode, WithHole, WithSize,
    WithAlpha, WithFillColor, WithStroke, WithStrokeColor {

    /**
     * Gets the Geom object specific to **pie** layers.
     *
     * @return the [Geom] object for **pie**.
     */
    override val geom: Geom
        get() = PIE

    /**
     * Gets the set of required aesthetics for **pie** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>  // todo
        get() = setOf()
}

/**
 * Context class for managing Pie layers.
 *
 * This class provides the context in which Pie layers can be configured.
 * It inherits from [LayerContext] and implements the [PieInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class PieContext(parent: LayerCollectorContext) : LayerContext(parent), PieInterface
