package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithType
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithWidthAsSize
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

// todo doc
@Suppress("INVISIBLE_REFERENCE")
public class BorderLine @PublishedApi internal constructor(
    bindingHandler: BindingHandler
) : SelfInvocationContext, WithColor, WithType, WithWidthAsSize, SubContext(bindingHandler)