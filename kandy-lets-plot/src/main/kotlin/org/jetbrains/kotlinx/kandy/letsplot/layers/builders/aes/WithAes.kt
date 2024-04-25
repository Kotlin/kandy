package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.SubContext

// todo doc
public interface WithAes

@Suppress("INVISIBLE_REFERENCE")
internal val WithAes.bindingHandler: BindingHandler
    @Suppress("INVISIBLE_MEMBER")
    get() = if (this is SubContext) {
        bindingHandler
    } else (this as LayerBuilder).bindingHandler
