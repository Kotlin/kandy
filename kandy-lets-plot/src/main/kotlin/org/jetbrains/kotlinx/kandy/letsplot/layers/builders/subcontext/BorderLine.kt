@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithType
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithWidthAsSize
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

/**
 * TODO(https://github.com/Kotlin/kandy/issues/414)
 */
public class BorderLine @PublishedApi internal constructor(
    bindingHandler: BindingHandler
) : SelfInvocationContext, WithColor, WithType, WithWidthAsSize, SubContext(bindingHandler)