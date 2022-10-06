/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import kotlin.reflect.KProperty

public inline fun <reified T : Any> KProperty<T>.toColumnPointer(): ColumnPointer<T> =
    ColumnPointer(this.name)


