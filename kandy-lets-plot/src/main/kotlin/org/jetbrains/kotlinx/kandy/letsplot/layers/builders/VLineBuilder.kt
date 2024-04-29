/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.VLineInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.V_LINE

/**
 * Context class for managing vertical line layers.
 *
 * This class serves as the context in which vertical line layers can be customized.
 * It inherits from [LayerContext] and implements the [VLineInterface].
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class VLineBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), VLineInterface {
    /**
     * Gets the Geom object specific to **vLine** layers.
     *
     * @return the [Geom] object for **vLine**.
     */
    internal override val geom: Geom
        get() = V_LINE

    /**
     * Gets the set of required aesthetics for **vLine** layers.
     *
     * @return the set of required aesthetics.
     */
    internal override val requiredAes: Set<Aes>
        get() = setOf(X_INTERCEPT)
}
