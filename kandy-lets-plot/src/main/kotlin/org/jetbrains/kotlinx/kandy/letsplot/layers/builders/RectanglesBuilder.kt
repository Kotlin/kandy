/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_MIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LayerWithBorderLineBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.RectanglesBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.RECT

/**
 * Builder class for managing rectangle layers.
 *
 * This class provides the context in which rectangle layers can be configured.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [RectanglesBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class RectanglesBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerWithBorderLineBuilder(parent, datasetIndex), RectanglesBuilderInterface {
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
