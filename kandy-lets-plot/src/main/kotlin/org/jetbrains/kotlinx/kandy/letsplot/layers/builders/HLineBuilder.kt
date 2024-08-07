/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.HLineBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.H_LINE

/**
 * Builder class for managing hLine layers.
 *
 * This class provides the context in which hLine layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [HLineBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */

@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class HLineBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), HLineBuilderInterface {
    /**
     * Gets the Geom object specific to **hLine** layers.
     *
     * @return the [Geom] object for **hLine**.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(Y_INTERCEPT)

    /**
     * Gets the set of required aesthetics for **hLine** layers.
     *
     * @return the set of required aesthetics.
     */
    override val geom: Geom
        get() = H_LINE
}
