/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LayerWithBorderLineBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.TilesBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TILE

/**
 * Context class for managing tile layers.
 *
 * This class serves as the context in which tile layers can be customized.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [TilesBuilderInterface].
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class TilesBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerWithBorderLineBuilder(parent, datasetIndex), TilesBuilderInterface {
    /**
     * Gets the Geom object specific to **tiles** layers.
     *
     * @return the [Geom] object for **tiles**.
     */
    internal override val geom: Geom
        get() = TILE

    /**
     * Gets the set of required aesthetics for **tiles** layers.
     *
     * @return the set of required aesthetics.
     */
    internal override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
    }
