/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT

/**
 * Interface defining the necessary aesthetics and methods for points layers.
 *
 * Points layers are primarily used in scatter plots to represent individual data points in two-dimensional space.
 * The interface provides aesthetics such as `x`, `y`, `color`, `symbol`, `size`, `alpha`, `stroke`, and `fillColor`
 * to allow for a comprehensive customization of point representation.
 *
 * Required aesthetics for points layers are `x` and `y`.
 */
public interface PointsInterface : LayerContextInterface, WithX, WithY, WithColor, WithSymbol,
    WithSize, WithAlpha, WithFillColor, WithPointStroke {

    /**
     * Gets the Geom object specific to **points** layers.
     *
     * @return the [Geom] object for **points**.
     */
    override val geom: Geom
        get() = POINT

    /**
     * Gets the set of required aesthetics for **points** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

/**
 * Context class for managing points layers.
 *
 * This class provides the context in which points layers can be configured.
 * It inherits from [LayerContext] and implements the [PointsInterface].
 *
 * @param parent the parent context for the layer.
 */

public open class PointsContext(parent: LayerCollectorContext) : LayerContext(parent), PointsInterface
