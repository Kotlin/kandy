/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData

/**
 * Mutual version of [NamedData].
 */
typealias MutableNamedData = MutableMap<String, List<Any>>
