package org.jetbrains.kotlinx.kandy.dsl.impl

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.bindingHandler

// todo doc
public interface WithAes

@Suppress("INVISIBLE_REFERENCE")
internal val WithAes.bindingHandler: BindingHandler
    @Suppress("INVISIBLE_MEMBER")
    get() = (this as LayerBuilder).bindingHandler
