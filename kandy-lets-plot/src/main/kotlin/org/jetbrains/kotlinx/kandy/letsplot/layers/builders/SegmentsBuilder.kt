/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.SegmentsBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.SEGMENT

/**
 * Builder class for managing segment layers.
 *
 * This class provides the context in which segment layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [SegmentsBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class SegmentsBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), SegmentsBuilderInterface {
    /**
     * Gets the Geom object specific to **segment** layers.
     *
     * @return the [Geom] object for **segment**.
     */
    override val geom: Geom
        get() = SEGMENT

    /**
     * Gets the set of required aesthetics for **segment** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X_BEGIN, Y_BEGIN, X_END, Y_END)
}
