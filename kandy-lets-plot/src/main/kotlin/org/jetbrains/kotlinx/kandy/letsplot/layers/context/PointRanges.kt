/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MAX
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y_MIN
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.POINT_RANGE
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


public class InnerPointContext internal constructor(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithSymbol, WithFillColor, WithFatten, WithPointStroke

public class InnerLineContext internal constructor(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithType

/**
 * Interface defining the necessary aesthetics and methods for pointRange layers.
 *
 * PointRange layers are used to represent points along a range,
 * which is often used in scatter plots, range plots, and other kinds of data visualizations.
 * The interface provides aesthetics such as `x`, `y`, `yMin`, `yMax`, `alpha`, `color`, and `size`.
 * It also introduces `innerPoint` and `innerLine` contexts for more granular customization.
 *
 * Required aesthetics for pointRange layers are `x`, `y`, `yMin`, and `yMax`.
 */
public interface PointRangesInterface : LayerContextInterface, WithX, WithY, WithAlpha,
    WithColor, WithYMin, WithYMax, WithSize {
    public val innerPoint: InnerPointContext
    public val innerLine: InnerLineContext

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

/**
 * Context class for managing pointRange layers.
 *
 * This class provides the context in which pointRange layers can be configured.
 * It inherits from [LayerContext] and implements the [PointRangesInterface].
 * It also initializes `innerPoint` and `innerLine` contexts for more granular customization.
 *
 * @param parent the parent context for the layer.
 */
public open class PointRangesContext(parent: LayerCollectorContext) : LayerContext(parent), PointRangesInterface {
    // todo fix
    public override val innerPoint: InnerPointContext = InnerPointContext(this)
    public override val innerLine: InnerLineContext = InnerLineContext(this)
}
