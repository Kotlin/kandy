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
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.STEP

/**
 * Interface defining the necessary aesthetics and methods for step layers.
 *
 * Step layers are used for step charts,
 * which display data points in a series connected by vertical and horizontal lines.
 * The interface provides aesthetics like `x`, `y`, `alpha`, `color`, `width`, and `lineType` for customization.
 *
 * Required aesthetics for step layers are `x` and `y`.
 */
public interface StepInterface : LayerContextInterface, WithX, WithY, WithAlpha, WithColor,
    WithWidthAsSize, WithLineType {

    /**
     * Gets the Geom object specific to **step** layers.
     *
     * @return the [Geom] object for **step**.
     */
    override val geom: Geom
        get() = STEP

    /**
     * Gets the set of required aesthetics for **step** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

/**
 * Context class for managing step layers.
 *
 * This class provides the context in which step layers can be configured.
 * It inherits from [LayerContext] and implements the [StepInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class StepContext(parent: LayerCollectorContext) : LayerContext(parent), StepInterface
