/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.PositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.EXPLODE
import kotlin.reflect.KProperty

public interface WithExplode : BindingContext {
    /*public val explode: ConstantSetter
        get() = ConstantSetter(EXPLODE, bindingCollector)
*/
    /*
    public fun <T> xMin(value: T): PositionalSetting<T> {
        return addPositionalSetting(X_MIN, value)
    }

     */

    public fun <T> explode(
        column: ColumnReference<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(EXPLODE, column.name(), null)
    }

    public fun <T> explode(
        column: KProperty<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(EXPLODE, column.name, null)
    }

    public fun <T> explode(
        column: String,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(EXPLODE, column, null)
    }

    public fun <T> explode(
        values: Iterable<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            EXPLODE,
            values.toList(),
            null,
            null
        )
    }

    public fun <T> explode(
        values: DataColumn<T>,
    ): PositionalMapping<T> {
        return addPositionalMapping<T>(
            EXPLODE,
            values,
            null
        )
    }
}