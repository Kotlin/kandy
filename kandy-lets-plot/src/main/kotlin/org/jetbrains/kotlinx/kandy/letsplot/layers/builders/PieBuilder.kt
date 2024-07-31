/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.PieBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.PIE

/**
 * Builder class for managing Pie layers.
 *
 * This class provides the context in which Pie layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [PieBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class PieBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope, datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), PieBuilderInterface {

    /**
     * Gets the Geom object specific to **pie** layers.
     *
     * @return the [Geom] object for **pie**.
     */
    override val geom: Geom
        get() = PIE

    /**
     * Gets the set of required aesthetics for **pie** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>  // TODO(https://github.com/Kotlin/kandy/issues/279)
        get() = setOf()
}
