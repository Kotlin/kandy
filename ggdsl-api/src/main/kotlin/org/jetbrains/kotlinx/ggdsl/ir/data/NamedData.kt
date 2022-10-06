/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.data

/**
 * Dataset in the form of [Map].
 * Keys are column names.
 * Values are columns of data.
 * All values lists must be the same size.
 */
public typealias NamedData = Map<String, List<Any>>
