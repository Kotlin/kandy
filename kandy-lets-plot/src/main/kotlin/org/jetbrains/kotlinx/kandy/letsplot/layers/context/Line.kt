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
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.LINE

/**
 * Interface defining the necessary aesthetics and methods for line layers.
 *
 * Line layers are often used to connect data points in the order in which they appear in the data frame.
 * The interface provides aesthetics such as `x`, `y`,
 * `alpha`, and `color` to allow for full customization of the lines.
 *
 * Required aesthetics for line are `x` and `y`.
 */
public interface LineInterface : LayerContextInterface, WithX, WithY, WithAlpha, WithColor,
    WithWidthAsSize, WithType {

    /**
     * Gets the Geom object specific to **line** layers.
     *
     * @return the [Geom] object for **line**.
     */
    override val geom: Geom
        get() = LINE

    /**
     * Gets the set of required aesthetics for **line** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

/**
 * Context class for managing line layers.
 *
 * This class provides the context in which line layers can be configured.
 * It inherits from [LayerContext] and implements the [LineInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class LineContext(parent: LayerCollectorContext) : LayerContext(parent), LineInterface
