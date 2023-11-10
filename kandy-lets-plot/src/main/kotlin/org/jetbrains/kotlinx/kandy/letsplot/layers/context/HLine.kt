/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.H_LINE

/**
 * Interface defining the necessary aesthetics and methods for hLine layers.
 *
 * hLine layers are used to add a horizontal line at a specific yIntercept on a plot.
 * The interface provides aesthetics such as `yIntercept`, `alpha`, `color`, `width`, `type`,
 * `x` to allow for full customization of the horizontal lines.
 *
 * Required aesthetics for hLine are `yIntercept`.
 */
public interface HLineInterface : LayerContextInterface, WithYIntercept, WithAlpha, WithColor,
    WithWidthAsSize, WithType, WithXFree {

    /**
     * Gets the Geom object specific to **hLine** layers.
     *
     * @return the [Geom] object for **hLine**.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(Y_INTERCEPT)

    /**
     * Gets the set of required aesthetics for **hLine** layers.
     *
     * @return the set of required aesthetics.
     */
    override val geom: Geom
        get() = H_LINE
}

/**
 * Context class for managing hLine layers.
 *
 * This class provides the context in which hLine layers can be configured.
 * It inherits from [LayerContext] and implements the [HLineInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class HLineContext(parent: LayerCollectorContext) : LayerContext(parent), HLineInterface
