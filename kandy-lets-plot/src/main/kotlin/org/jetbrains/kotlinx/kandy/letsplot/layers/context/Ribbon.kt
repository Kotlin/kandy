/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RIBBON

/**
 * Interface defining the necessary aesthetics and methods for ribbon layers.
 *
 * Ribbon layers are generally used to visualize a range around a line, such as confidence intervals.
 * This interface provides aesthetics like `x`, `yMin`, `yMax`, `fillColor`, and `alpha` for customization.
 *
 * Required aesthetics for ribbon layers are `x`, `yMin`, and `yMax`.
 */
public interface RibbonInterface : LayerContextInterface, WithX, WithYMin,
    WithYMax, WithFillColor, WithAlpha, WithYFree {

    /**
     * Gets the Geom object specific to **ribbon** layers.
     *
     * @return the [Geom] object for **ribbon**.
     */
    override val geom: Geom
        get() = RIBBON

    /**
     * Gets the set of required aesthetics for **ribbon** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y_MIN, Y_MAX)
}


/**
 * Context class for managing ribbon layers.
 *
 * This class provides the context in which ribbon layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [RibbonInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class RibbonContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), RibbonInterface
