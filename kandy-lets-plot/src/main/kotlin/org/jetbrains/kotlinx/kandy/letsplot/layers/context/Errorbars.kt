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
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.ERROR_BAR

/**
 * Interface defining the necessary aesthetics and methods for ErrorBars layers.
 *
 * ErrorBars are used to represent the variability of data in scatter and line plots.
 * This interface provides various aesthetics like `x`, `yMin`, `yMax`, `width`, `alpha`,
 * and `y` to enable full customization.
 *
 * Required aesthetics for ErrorBars are `X`, `Y_MIN`, and `Y_MAX`.
 */
public interface ErrorBarsInterface : LayerContextInterface, WithX, WithYMin,
    WithYMax, WithWidth, WithAlpha, WithYFree {

    /**
     * Gets the Geom object specific to **errorBars** layers.
     *
     * @return the [Geom] object for **errorBars**.
     */
    override val geom: Geom
        get() = ERROR_BAR

    /**
     * Gets the set of required aesthetics for **errorBars** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y_MIN, Y_MAX)
}

/**
 * Context class for managing ErrorBars layers.
 *
 * This class provides the context in which ErrorBars layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [ErrorBarsInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class ErrorBarsContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent),
    ErrorBarsInterface
