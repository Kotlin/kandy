/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import kotlin.reflect.KProperty
import kotlin.reflect.typeOf

inline fun <reified T : Any> KProperty<T>.toDataSource(): DataSource<T> =
    DataSource(this.name, typeOf<T>())

