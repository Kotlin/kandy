package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import kotlin.reflect.KProperty

inline fun <reified T : Any> KProperty<T>.toColumnPointer(): ColumnPointer<T> =
    ColumnPointer(this.name)

