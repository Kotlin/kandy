/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.X_END
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_BEGIN
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_END
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.SegmentsInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.SEGMENT

/**
 * Context class for managing segment layers.
 *
 * This class provides the context in which segment layers can be configured.
 * It inherits from [LayerContext] and implements the [SegmentsInterface].
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class SegmentsBuilder @PublishedApi internal constructor(parent: LayerCreatorScope) : LayerBuilderImpl(parent),
    SegmentsInterface {
    /**
     * Gets the Geom object specific to **segment** layers.
     *
     * @return the [Geom] object for **segment**.
     */
    internal override val geom: Geom
    get() = SEGMENT

    /**
     * Gets the set of required aesthetics for **segment** layers.
     *
     * @return the set of required aesthetics.
     */
    internal override val requiredAes: Set<Aes>
    get() = setOf(X_BEGIN, Y_BEGIN, X_END, Y_END)
}
