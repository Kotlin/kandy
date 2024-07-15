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
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LayerWithBorderLineBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LineRangesBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.LINE_RANGE

/**
 * Context class for managing lineRanges layers.
 *
 * This class provides the context in which lineRanges layers can be configured.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [LineRangesBuilderInterface].
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class LineRangesBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerWithBorderLineBuilder(parent, datasetIndex), LineRangesBuilderInterface {
    /**
     * Gets the Geom object specific to **lineRanges** layers.
     *
     * @return the [Geom] object for **lineRanges**.
     */
    override val geom: Geom
        get() = LINE_RANGE

    /**
     * Gets the set of required aesthetics for **lineRanges** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y_MIN, Y_MAX)
}
