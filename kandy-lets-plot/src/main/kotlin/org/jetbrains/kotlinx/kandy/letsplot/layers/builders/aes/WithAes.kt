@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.bindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.SubContext

/**
 * Interface for managing the aesthetic properties in [LayerBuilder].
 */
public interface WithAes

@PublishedApi
internal val WithAes.bindingHandler: BindingHandler
    get() = if (this is SubContext) {
        bindingHandler
    } else (this as LayerBuilder).bindingHandler
