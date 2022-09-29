package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.Symbol
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SourceScaledTest {
    @Test
    fun testScaledUnspecified() {
        val ds = ColumnPointer<Int>("ds1")
        val scaledSource = ds.scaled()
        assertEquals(SourceScaledUnspecifiedDefault(ds), scaledSource)
    }

    @Test
    fun testScaledPositionalDefault() {
        val ds = ColumnPointer<Double>("ds2")
        val continuousScaledSource = ds.scaled(PositionalContinuousUnspecifiedScale())
        val categoricalScaledSource = ds.scaled(PositionalCategoricalUnspecifiedScale)
        assertEquals(
            SourceScaledPositionalUnspecified(ds, PositionalContinuousUnspecifiedScale()),
            continuousScaledSource
        )
        assertEquals(
            SourceScaledPositionalUnspecified(ds, PositionalCategoricalUnspecifiedScale),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositionalDefault() {
        val ds = ColumnPointer<Double>("ds3")
        val continuousScaledSource = ds.scaled(NonPositionalContinuousUnspecifiedScale())
        val categoricalScaledSource = ds.scaled(NonPositionalCategoricalUnspecifiedScale)
        assertEquals(
            SourceScaledNonPositionalUnspecified(ds, NonPositionalContinuousUnspecifiedScale()),
            continuousScaledSource
        )
        assertEquals(
            SourceScaledNonPositionalUnspecified(ds, NonPositionalCategoricalUnspecifiedScale),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledPositional() {
        val ds1 = ColumnPointer<Float>("ds4")
        val scale1 = PositionalContinuousScale(limits = 4.3F to 10F)
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            SourceScaledPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = ColumnPointer<String>("ds10")
        val scale2 = PositionalCategoricalScale(listOf<String>())
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            SourceScaledPositional(ds2, scale2),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositional() {
        val ds1 = ColumnPointer<Char>("dsX")
        val scale1 = NonPositionalContinuousScale(
            'a' to 'e',
            Color.fromHex("#000000") to Color.fromName("red")
        )
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            SourceScaledNonPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = ColumnPointer<String>("dsY")
        val scale2 = NonPositionalCategoricalScale<String, Symbol>(
            rangeValues = listOf(Symbol.CIRCLE, Symbol.TRIANGLE)
        )
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            SourceScaledNonPositional(ds2, scale2),
            categoricalScaledSource
        )
    }
}
