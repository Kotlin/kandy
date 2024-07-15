@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithType
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public class InnerLine @PublishedApi internal constructor(bindingHandler: BindingHandler) :
    SelfInvocationContext, WithType, SubContext(bindingHandler)
