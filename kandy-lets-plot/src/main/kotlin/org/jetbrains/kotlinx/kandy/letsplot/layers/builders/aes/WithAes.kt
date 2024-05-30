package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.SubContext

/**
 * Interface for managing the aesthetic properties in [LayerBuilder].
 */
public interface WithAes

@Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
internal val WithAes.bindingHandler: BindingHandler
    get() = if (this is SubContext) {
        bindingHandler
    } else (this as LayerBuilder).bindingHandler
