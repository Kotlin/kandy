package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithAes

@Suppress("INVISIBLE_REFERENCE")
public abstract class SubContext internal constructor(internal val bindingHandler: BindingHandler) : WithAes