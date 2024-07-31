/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.LABEL
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.bindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.TextBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.Font
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.TEXT


/**
 * Builder class for managing text layers.
 *
 * This class provides the context in which text layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [TextBuilderInterface].
 * It also contains a nested `FontContext` object for managing font styling.
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class TextBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), TextBuilderInterface {
    /**
     * TODO(https://github.com/Kotlin/kandy/issues/414)
     */
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
