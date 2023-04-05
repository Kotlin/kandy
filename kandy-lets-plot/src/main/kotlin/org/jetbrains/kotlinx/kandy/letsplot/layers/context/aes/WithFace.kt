package org.jetbrains.kotlinx.kandy.letsplot.layers.context.aes

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.columns.ColumnReference
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingContext
import org.jetbrains.kotlinx.kandy.ir.bindings.NonPositionalMapping
import org.jetbrains.kotlinx.kandy.letsplot.internal.FONT_FACE
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotNonPositionalMappingParameters
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
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFace>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            column.name(),
            LetsPlotNonPositionalMappingParameters<T, FontFace>().apply(parameters)
        )
    }

    public fun <T> face(
        column: KProperty<T>,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFace>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            column.name,
            LetsPlotNonPositionalMappingParameters<T, FontFace>().apply(parameters)
        )
    }

    public fun face(
        column: String,
        parameters: LetsPlotNonPositionalMappingParameters<Any?, FontFace>.() -> Unit = {}
    ): NonPositionalMapping<Any?, FontFace> {
        return addNonPositionalMapping<Any?, FontFace>(
            FONT_FACE,
            column,
            LetsPlotNonPositionalMappingParameters<Any?, FontFace>().apply(parameters)
        )
    }

    // Iterable, Array, PrimArray, DataColumn,
    public fun <T> face(
        values: Iterable<T>,
        name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFace>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            values.toList(),
            name,
            LetsPlotNonPositionalMappingParameters<T, FontFace>().apply(parameters)
        )
    }

    public fun <T> face(
        values: DataColumn<T>,
        //name: String? = null,
        parameters: LetsPlotNonPositionalMappingParameters<T, FontFace>.() -> Unit = {}
    ): NonPositionalMapping<T, FontFace> {
        return addNonPositionalMapping<T, FontFace>(
            FONT_FACE,
            values,
            LetsPlotNonPositionalMappingParameters<T, FontFace>().apply(parameters)
        )
    }
}