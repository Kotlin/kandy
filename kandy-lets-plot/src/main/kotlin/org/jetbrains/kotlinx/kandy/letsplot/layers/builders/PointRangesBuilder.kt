/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.bindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.PointRangesBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.InnerLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.InnerPoint
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT_RANGE


/**
 * Builder class for managing pointRange layers.
 *
 * This class provides the context in which pointRange layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [PointRangesBuilderInterface].
 * It also initializes `innerPoint` and `innerLine` contexts for more granular customization.
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class PointRangesBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), PointRangesBuilderInterface {

    /**
     * TODO(https://github.com/Kotlin/kandy/issues/414)
     */
    public override val innerPoint: InnerPoint = InnerPoint(bindingHandler)

    /**
     * TODO(https://github.com/Kotlin/kandy/issues/414)
     */
    public override val innerLine: InnerLine = InnerLine(bindingHandler)

    /**
     * Gets the Geom object specific to **pointRange** layers.
     *
     * @return the [Geom] object for **pointRange**.
     */
    override val geom: Geom
        get() = POINT_RANGE

    /**
     * Gets the set of required aesthetics for **pointRange** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y, Y_MIN, Y_MAX)
}
