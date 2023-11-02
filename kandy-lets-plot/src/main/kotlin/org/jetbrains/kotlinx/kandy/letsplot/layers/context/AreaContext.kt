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
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AREA

/**
 * Interface defining the necessary aesthetics and methods for Area layers.
 *
 * Area layers are used to represent the space between a line and a baseline, often to indicate volume or
 * quantities over a range of values.
 * The interface provides aesthetics: `x`, `y`, `alpha`, `fillColor`, to allow customization.
 *
 * Required aesthetics for Area are `X` and `Y`.
 */
public interface AreaInterface : LayerContextInterface, WithBorderLine, WithX, WithY, WithAlpha,
    WithFillColor {

    /**
     * Gets the Geom object specific to **area** layers.
     *
     * @return the [Geom] object for *area*.
     */
    override val geom: Geom get() = AREA

    /**
     * Gets the set of required aesthetics for **area** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes> get() = setOf(X, Y)
}

/**
 * Context class for managing Area layers.
 *
 * This class provides the context in which Area layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [AreaInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class AreaContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), AreaInterface
