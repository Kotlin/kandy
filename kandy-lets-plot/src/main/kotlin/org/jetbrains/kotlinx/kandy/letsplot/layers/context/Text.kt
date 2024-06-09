/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TEXT
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext


public class FontContext(override val parentContext: BindingContext) :
    SelfInvocationContext, SubBindingContext, WithColor, WithSize, WithFace, WithFamily

/**
 * Interface defining the necessary aesthetics and methods for text layers.
 *
 * Text layers are used to display text labels on plots, typically indicating data points or other elements.
 * The interface provides aesthetics like `x`, `y`, `alpha`, and `label` for customization.
 * Additionally, it contains a nested `FontContext` for font styling.
 *
 * Required aesthetics for text layers are `X`, `Y`, and `LABEL`.
 */
public interface TextInterface : LayerContextInterface, WithX, WithY, WithAlpha, WithLabel {

    /**
     * Gets the Geom object specific to **text** layers.
     *
     * @return the [Geom] object for **text**.
     */
    override val geom: Geom
        get() = TEXT

    /**
     * Gets the set of required aesthetics for **text** layers.
     *
     * @return the set of required aesthetics.
     */
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y, LABEL)

    public var font: FontContext
}

/**
 * Context class for managing text layers.
 *
 * This class provides the context in which text layers can be configured.
 * It inherits from [LayerContext] and implements the [TextInterface].
 * It also contains a nested `FontContext` object for managing font styling.
 *
 * @param parent the parent context for the layer.
 */
public open class TextContext(parent: LayerCollectorContext) : LayerContext(parent), TextInterface {

    override var font: FontContext = FontContext(this)

    public inline fun font(block: FontContext.() -> Unit){
        font = FontContext(this).apply(block)
    }
}
