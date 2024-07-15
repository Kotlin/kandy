@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFatten
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFillColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithPointStroke
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithSymbol
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public class InnerPoint @PublishedApi internal constructor(bindingHandler: BindingHandler) :
    SelfInvocationContext, WithSymbol, WithFillColor, WithFatten, WithPointStroke, SubContext(bindingHandler)
