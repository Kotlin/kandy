/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.util.context

public interface SelfInvocationContext

public operator fun <T : SelfInvocationContext> T.invoke(block: T.() -> Unit): T = apply(block)