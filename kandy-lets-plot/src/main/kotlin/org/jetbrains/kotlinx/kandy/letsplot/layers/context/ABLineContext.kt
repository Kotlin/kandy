/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLOPE
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AB_LINE

/**
 * Interface defining the necessary aesthetics and methods for ABLine layers.
 *
 * ABLine layers are often used to add a line with a specific slope and intercept to a plot.
 * The interface inherits provides aesthetics: `type`, `color`, `alpha`, `width`, `slope`, `intercept`, `x`, `y`,
 * to allow customization.
 *
 * Required aesthetics for ABLine are `SLOPE` and `INTERCEPT`.
 */
public interface ABLineInterface : LayerContextInterface, WithType,
    WithColor, WithAlpha,
    WithWidthAsSize, WithSlope, WithIntercept,
    WithXFree, WithYFree {


    /**
     * Gets the Geom object specific to **abLine** layers.
     *
     * @return the [Geom] object for **abLine**.
     */
    override val geom: Geom
        get() = AB_LINE

    /**
     * Gets the set of required aesthetics for **abLine** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(SLOPE, INTERCEPT)
}

/**
 * Context class for managing ABLine layers.
 *
 * This class provides the context in which ABLine layers can be configured.
 * It inherits from [LayerContext] and implements the [ABLineInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class ABLineContext(parent: LayerCollectorContext) : LayerContext(parent), ABLineInterface
