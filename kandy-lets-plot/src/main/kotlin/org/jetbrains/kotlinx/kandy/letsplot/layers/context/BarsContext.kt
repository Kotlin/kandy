/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BAR

/**
 * Interface defining the necessary aesthetics and methods for Bars layers.
 *
 * Bars layers are used to create bar charts, which are often used to represent categorical data.
 * The interface provides aesthetics such as `x`, `y`, `alpha`, `fillColor`,`width`,
 * and `borderLine` customization to allow for full customization of the bars.
 *
 * Required aesthetics for Bars are `X` and `Y`.
 */
public interface BarsContextInterface : LayerContextInterface, WithBorderLine, WithX, WithY,
    WithAlpha, WithFillColor, WithWidth {

    /**
     * Gets the Geom object specific to **bars** layers.
     *
     * @return the [Geom] object for **bars**.
     */
    public override val geom: Geom
        get() = BAR

    /**
     * Gets the set of required aesthetics for **bars** layers.
     *
     * @return the set of required aesthetics.
     */
    public override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}

/**
 * Context class for managing Bars layers.
 *
 * This class provides the context in which Bars layers can be configured.
 * It inherits from [LayerWithBorderLineContext] and implements the [BarsContextInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class BarsContext(parent: LayerCollectorContext) : LayerWithBorderLineContext(parent),
    BarsContextInterface
