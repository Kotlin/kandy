package org.jetbrains.kotlinx.ggdsl.util.context

interface SelfInvocationContext

operator fun <T : SelfInvocationContext> T.invoke(block: T.() -> Unit) = apply(block)