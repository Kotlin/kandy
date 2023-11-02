/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BOXPLOT

/**
 * Interface defining the necessary aesthetics and methods for Boxes layers.
 *
 * Boxes are often used to represent the distribution and spread of a dataset.
 * This interface provides various aesthetics like `x`, `alpha`, `fillColor`, `width`,
 * `lower`, `upper`, `middle`, `yMin`, `yMax`, and `fatten` for full customization of the boxplot.
 *
 * Required aesthetics for Boxplot are `X`, `LOWER`, `UPPER`, `MIDDLE`, `Y_MIN`, and `Y_MAX`.
 */
public interface BoxesInterface : LayerContextInterface, WithBorderLine, WithX, WithAlpha,
    WithFillColor, WithWidth,
    WithLower, WithUpper, WithMiddle, WithYMin, WithYMax, WithFatten, WithYFree {

    /**
     * Gets the Geom object specific to **boxplot** layers.
     *
     * @return the [Geom] object for **boxes**.
     */
    override val geom: Geom
        get() = BOXPLOT

    /**
     * Gets the set of required aesthetics for **boxplot** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes> get() = setOf(X, LOWER, UPPER, MIDDLE, Y_MIN, Y_MAX)
}


/**
 * Context class for managing Boxplot layers.
 *
 * This class provides the context in which Boxplot layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [BoxplotInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class BoxesContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), BoxesInterface
