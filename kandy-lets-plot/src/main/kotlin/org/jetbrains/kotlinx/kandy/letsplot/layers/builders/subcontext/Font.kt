package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext

import org.jetbrains.kotlinx.kandy.dsl.internal.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFace
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFamily
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithSize
import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

// TODO doc
@Suppress("INVISIBLE_REFERENCE")
public class Font @PublishedApi internal constructor(
    bindingHandler: BindingHandler
) : SelfInvocationContext, WithColor, WithSize, WithFace, WithFamily, SubContext(bindingHandler)
