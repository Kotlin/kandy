package org.jetbrains.kotlinx.ggdsl.dsl

import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import kotlin.reflect.KProperty
import kotlin.reflect.typeOf

inline fun <reified T : Any> KProperty<T>.toDataSource(): DataSource<T> =
    DataSource(this.name, typeOf<T>())

