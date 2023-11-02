/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RECT

/**
 * Interface defining the necessary aesthetics and methods for rectangle layers.
 *
 * Rectangle layers are typically used for creating visual representations of rectangular shapes,
 * such as in histograms or heatmaps.
 * The interface provides aesthetics including `xMin`, `xMax`, `yMin`,
 * `yMax`, `fillColor`, and `alpha` for complete customization of rectangles.
 *
 * Required aesthetics for rectangle layers are `xMin`, `xMax`, `yMin`, and `yMax`.
 */
public interface RectanglesInterface : LayerContextInterface, WithXMin, WithXMax,
    WithYMin, WithYMax,
    WithFillColor, WithAlpha, WithXFree, WithYFree {

    /**
     * Gets the Geom object specific to **rect** layers.
     *
     * @return the [Geom] object for **rect**.
     */
    override val geom: Geom
        get() = RECT

    /**
     * Gets the set of required aesthetics for **rect** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X_MIN, X_MAX, Y_MIN, Y_MAX)
}

/**
 * Context class for managing rectangle layers.
 *
 * This class provides the context in which rectangle layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [RectanglesInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class RectanglesContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent),
    RectanglesInterface
