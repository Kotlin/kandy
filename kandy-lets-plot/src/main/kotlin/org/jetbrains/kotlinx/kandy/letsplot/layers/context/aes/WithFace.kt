/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FACE
import org.jetbrains.kotlinx.kandy.letsplot.util.font.FontFace
import kotlin.reflect.KProperty

public interface WithFace : BindingContext {
    public var face: FontFace?
        get() = null
        set(value) {
            addNonPositionalSetting(FONT_FACE, value)
        }

    public fun <T> face(
        column: ColumnReference<T>,
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            column.name(),
            null
        )
    }

    public fun <T> face(
        column: KProperty<T>,
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            column.name,
            null
        )
    }

    public fun face(
        column: String,
    ): NonPositionalMapping<Any?, FontFace> {
        return addNonPositionalMapping<Any?, FontFace>(
            FONT_FACE,
            column,
            null
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> face(
        values: Iterable<T>,
        name: String? = null
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            values.toList(),
            name,
            null
        )
    }

    public fun <T> face(
        values: DataColumn<T>,
        //name: String? = null,
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            values,
            null
        )
    }
}