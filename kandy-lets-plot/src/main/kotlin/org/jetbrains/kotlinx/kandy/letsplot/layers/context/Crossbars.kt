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
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.CROSS_BAR

/**
 * Interface defining the necessary aesthetics and methods for CrossBars layers.
 *
 * CrossBars are used to represent a central tendency measure and spread of a dataset
 * through horizontal lines crossing the bars.
 * This interface provides various aesthetics like `x`, `y`, `yMin`, `yMax`, `fatten`, `width`,
 * `fillColor`, and `alpha` to enable full customization.
 *
 * Required aesthetics for CrossBars are `X`, `Y`, `Y_MIN`, `Y_MAX`.
 */
public interface CrossBarsInterface : LayerContextInterface, WithBorderLine, WithX, WithY, WithYMin, WithYMax,
    WithFatten, WithWidth, WithFillColor, WithAlpha {

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
        get() = setOf(X, Y, Y_MIN, Y_MAX)
}

/**
 * Context class for managing CrossBars layers.
 *
 * This class provides the context in which CrossBars layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [CrossBarsInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class CrossBarsContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent),
    CrossBarsInterface
