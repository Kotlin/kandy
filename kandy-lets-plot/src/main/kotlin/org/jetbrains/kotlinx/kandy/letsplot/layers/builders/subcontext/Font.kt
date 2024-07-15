@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER")
package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.BindingHandler
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFace
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFamily
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithSize
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

// TODO doc
public class Font @PublishedApi internal constructor(
    bindingHandler: BindingHandler
) : SelfInvocationContext, WithColor, WithSize, WithFace, WithFamily, SubContext(bindingHandler)
