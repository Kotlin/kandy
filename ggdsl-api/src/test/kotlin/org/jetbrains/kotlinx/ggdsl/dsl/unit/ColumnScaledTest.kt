/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.ggdsl.dsl.Symbol
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ColumnScaledTest {
    @Test
    fun testScaledUnspecified() {
        val ds = column<Int>("ds1")
        val scaledSource = ds.scaled()
        assertEquals(ColumnScaledUnspecifiedDefault(ds), scaledSource)
    }

    @Test
    fun testScaledPositionalDefault() {
        val ds = column<Double>("ds2")
        val continuousScaledSource = ds.scaled(PositionalContinuousUnspecifiedScale())
        val categoricalScaledSource = ds.scaled(PositionalCategoricalUnspecifiedScale)
        assertEquals(
            ColumnScaledPositionalUnspecified(ds, PositionalContinuousUnspecifiedScale()),
            continuousScaledSource
        )
        assertEquals(
            ColumnScaledPositionalUnspecified(ds, PositionalCategoricalUnspecifiedScale),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositionalDefault() {
        val ds = column<Double>("ds3")
        val continuousScaledSource = ds.scaled(NonPositionalContinuousUnspecifiedScale())
        val categoricalScaledSource = ds.scaled(NonPositionalCategoricalUnspecifiedScale)
        assertEquals(
            ColumnScaledNonPositionalUnspecified(ds, NonPositionalContinuousUnspecifiedScale()),
            continuousScaledSource
        )
        assertEquals(
            ColumnScaledNonPositionalUnspecified(ds, NonPositionalCategoricalUnspecifiedScale),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledPositional() {
        val ds1 = column<Float>("ds4")
        val scale1 = PositionalContinuousScale<Float>(limits = 4.3F to 10F, null, null)
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            ColumnScaledPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = column<String>("ds10")
        val scale2 = PositionalCategoricalScale<String>(listOf<String>(), /*null*/)
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            ColumnScaledPositional(ds2, scale2),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositional() {
        val ds1 = column<Char>("dsX")
        val scale1 = NonPositionalContinuousScale<Char, Color>(
            'a' to 'e',
            Color.hex("#000000") to Color.named("red"),
            null,
            null
        )
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            ColumnScaledNonPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = column<String>("dsY")
        val scale2 = NonPositionalCategoricalScale<String, Symbol>(
            null,
            rangeValues = listOf(Symbol.CIRCLE, Symbol.TRIANGLE),
            //nullValue = null,
        )
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            ColumnScaledNonPositional(ds2, scale2),
            categoricalScaledSource
        )
    }
}
