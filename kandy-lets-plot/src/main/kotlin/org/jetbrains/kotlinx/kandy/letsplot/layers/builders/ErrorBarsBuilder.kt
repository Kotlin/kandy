/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.ErrorBarsInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LayerWithBorderLineBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.ERROR_BAR

/**
 * Context class for managing ErrorBars layers.
 *
 * This class provides the context in which ErrorBars layers can be configured.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [ErrorBarsInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class ErrorBarsBuilder @PublishedApi internal constructor(parent: LayerCreatorScope) : LayerWithBorderLineBuilder(parent),
    ErrorBarsInterface {
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
