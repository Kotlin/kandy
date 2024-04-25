/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.TextInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.Font
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TEXT
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.bindingHandler


/**
 * Context class for managing text layers.
 *
 * This class provides the context in which text layers can be configured.
 * It inherits from [LayerContext] and implements the [TextInterface].
 * It also contains a nested `FontContext` object for managing font styling.
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER")
public open class TextBuilder @PublishedApi internal constructor(parent: LayerCreatorScope) : LayerBuilderImpl(parent),
    TextInterface {
    override val font: Font = Font(bindingHandler)

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
}
