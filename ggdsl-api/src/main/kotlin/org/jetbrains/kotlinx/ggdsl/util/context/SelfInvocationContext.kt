/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.util.context

interface SelfInvocationContext

operator fun <T : SelfInvocationContext> T.invoke(block: T.() -> Unit) = apply(block)