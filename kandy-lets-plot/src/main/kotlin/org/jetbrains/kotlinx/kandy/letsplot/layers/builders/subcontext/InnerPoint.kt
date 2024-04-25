package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFatten
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFillColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithPointStroke
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithSymbol
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

@Suppress("INVISIBLE_REFERENCE")
public class InnerPoint @PublishedApi internal constructor(bindingHandler: BindingHandler) :
    SelfInvocationContext, WithSymbol, WithFillColor, WithFatten, WithPointStroke, SubContext(bindingHandler)
