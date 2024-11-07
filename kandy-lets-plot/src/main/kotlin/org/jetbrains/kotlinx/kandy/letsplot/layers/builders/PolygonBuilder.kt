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
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.PolygonsBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POLYGON

/**
 * Builder class for managing Polygons layers.
 *
 * This class provides the context in which `Polygons` layers can be configured.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [PolygonsBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER")
public open class PolygonBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerWithBorderLineBuilder(parent, datasetIndex), PolygonsBuilderInterface {
    /**
     * Gets the Geom object specific to **polygons** layers.
     *
     * @return the [Geom] object for **polygons**.
     */
    override val geom: Geom
        get() = POLYGON

    /**
     * Gets the set of required aesthetics for **polygons** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}
