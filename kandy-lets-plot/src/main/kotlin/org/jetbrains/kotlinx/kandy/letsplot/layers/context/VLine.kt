/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.V_LINE

/**
 * Interface for defining the necessary aesthetics and methods for vertical line layers.
 *
 * Vertical line layers are useful for indicating specific values along the X-axis.
 * This interface provides the required aesthetics like `xIntercept`,
 * `alpha`, `color`, `width`, and `type` for customization.
 *
 * The set of required aesthetics for vertical line layers includes `X_INTERCEPT`.
 */
public interface VLineInterface : LayerContextInterface, WithXIntercept, WithAlpha, WithColor,
    WithWidthAsSize, WithType, WithYFree {

    /**
     * Gets the Geom object specific to **vLine** layers.
     *
     * @return the [Geom] object for **vLine**.
     */
    override val geom: Geom
        get() = V_LINE

    /**
     * Gets the set of required aesthetics for **vLine** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X_INTERCEPT)
}

/**
 * Context class for managing vertical line layers.
 *
 * This class serves as the context in which vertical line layers can be customized.
 * It inherits from [LayerContext] and implements the [VLineInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class VLineContext(parent: LayerCollectorContext) : LayerContext(parent), VLineInterface
