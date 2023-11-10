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
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.LINE_RANGE

/**
 * Interface defining the necessary aesthetics and methods for lineRanges layers.
 *
 * LineRanges layers are used to display a range between a minimum and maximum y-value at each x-coordinate.
 * The interface provides aesthetics such as `x`, `yMin`, `yMax`, and `alpha` for full customization of the line ranges.
 *
 * Required aesthetics for lineRanges are `x`, `yMin`, and `yMax`.
 */
public interface LineRangesInterface : LayerContextInterface, WithX, WithYMin,
    WithYMax, WithAlpha, WithYFree {

    /**
     * Gets the Geom object specific to **lineRanges** layers.
     *
     * @return the [Geom] object for **lineRanges**.
     */
    override val geom: Geom
        get() = LINE_RANGE

    /**
     * Gets the set of required aesthetics for **lineRanges** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y_MIN, Y_MAX)
}

/**
 * Context class for managing lineRanges layers.
 *
 * This class provides the context in which lineRanges layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [LineRangesInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class LineRangesContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent),
    LineRangesInterface
