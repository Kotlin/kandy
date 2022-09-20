/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.ir.data

import kotlin.reflect.KType

// todo nullable?
/**
 * A source of data, i.e. a pointer to a column with the corresponding name.
 *
 * @param T a type of data
 * @property id the name of source in a dataset
 * @property type reified type of data
 */
public data class DataSource<T : Any>(val id: String, val type: KType)
