package org.jetbrains.kotlinx.ggdsl.util.context

public interface SelfInvocationContext

public operator fun <T : SelfInvocationContext> T.invoke(block: T.() -> Unit): T = apply(block)