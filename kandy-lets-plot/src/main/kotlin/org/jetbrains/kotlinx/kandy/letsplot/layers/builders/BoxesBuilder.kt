/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LayerWithBorderLineBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.BoxesBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BOXPLOT

/**
 * Context class for managing Boxes layers.
 *
 * This class provides the context in which `Boxes` layers can be configured.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [BoxesBuilderInterface].
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class BoxesBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerWithBorderLineBuilder(parent, datasetIndex), BoxesBuilderInterface {
    /**
     * Gets the Geom object specific to **boxes** layers.
     *
     * @return the [Geom] object for **boxes**.
     */
    override val geom: Geom
        get() = BOXPLOT

    /**
     * Gets the set of required aesthetics for **boxes** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, LOWER, UPPER, MIDDLE, Y_MIN, Y_MAX)
}
