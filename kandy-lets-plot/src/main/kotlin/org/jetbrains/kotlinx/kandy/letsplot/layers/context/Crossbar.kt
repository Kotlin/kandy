/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.MIDDLE
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.CROSS_BAR

/**
 * Interface defining the necessary aesthetics and methods for CrossBar layers.
 *
 * CrossBars are used to represent a central tendency measure and spread of a dataset
 * through horizontal lines crossing the bars.
 * This interface provides various aesthetics like `x`, `yMin`, `yMax`, `middle`, `fatten`, `width`,
 * `fillColor`, `alpha`, and `y` to enable full customization.
 *
 * Required aesthetics for CrossBar are `X`, `Y_MIN`, `Y_MAX`, and `MIDDLE`.
 */
public interface CrossBarInterface : LayerContextInterface, WithX, WithYMin,
    WithYMax, WithMiddle,
    WithFatten, WithWidth, WithFillColor, WithAlpha, WithYFree {

    /**
     * Gets the Geom object specific to **crossBars** layers.
     *
     * @return the [Geom] object for **crossBars**.
     */
    override val geom: Geom
        get() = CROSS_BAR

    /**
     * Gets the set of required aesthetics for **crossBars** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y_MIN, Y_MAX, MIDDLE)
}

/**
 * Context class for managing CrossBar layers.
 *
 * This class provides the context in which CrossBar layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [CrossBarInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class CrossBarContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent), CrossBarInterface
