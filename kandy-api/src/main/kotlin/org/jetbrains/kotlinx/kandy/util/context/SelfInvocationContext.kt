/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.util.context

/**
 * Base interface for context that can be invoked with [invoke] method, creating context
 * with this as a receiver and applies to this.
 */
public interface SelfInvocationContext

/**
 * Creates a context with this [SelfInvocationContext] as a receiver and applies to this.
 */
public operator fun <T : SelfInvocationContext> T.invoke(block: T.() -> Unit): T = apply(block)
