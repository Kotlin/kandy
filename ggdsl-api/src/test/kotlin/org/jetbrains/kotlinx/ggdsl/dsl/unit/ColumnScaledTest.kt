/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.Symbol
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typed
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedList
import org.jetbrains.kotlinx.ggdsl.dsl.scaled
import org.jetbrains.kotlinx.ggdsl.ir.bindings.*
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ColumnScaledTest {
    @Test
    fun testScaledUnspecified() {
        val ds = ColumnPointer<Int>("ds1")
        val scaledSource = ds.scaled()
        assertEquals(ColumnScaledUnspecifiedDefault(ds), scaledSource)
    }

    @Test
    fun testScaledPositionalDefault() {
        val ds = ColumnPointer<Double>("ds2")
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
        val ds = ColumnPointer<Double>("ds3")
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
        val ds1 = ColumnPointer<Float>("ds4")
        val scale1 = PositionalContinuousScale<Float>(limits = 4.3F.typed() to 10F.typed())
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            ColumnScaledPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = ColumnPointer<String>("ds10")
        val scale2 = PositionalCategoricalScale<String>(listOf<String>().typedList())
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            ColumnScaledPositional(ds2, scale2),
            categoricalScaledSource
        )
    }

    @Test
    fun testScaledNonPositional() {
        val ds1 = ColumnPointer<Char>("dsX")
        val scale1 = NonPositionalContinuousScale<Char, Color>(
            'a'.typed() to 'e'.typed(),
            Color.hex("#000000").typed() to Color.named("red").typed()
        )
        val continuousScaledSource = ds1.scaled(scale1)
        assertEquals(
            ColumnScaledNonPositional(ds1, scale1),
            continuousScaledSource
        )

        val ds2 = ColumnPointer<String>("dsY")
        val scale2 = NonPositionalCategoricalScale<String, Symbol>(
            rangeValues = listOf(Symbol.CIRCLE, Symbol.TRIANGLE).typedList()
        )
        val categoricalScaledSource = ds2.scaled(scale2)
        assertEquals(
            ColumnScaledNonPositional(ds2, scale2),
            categoricalScaledSource
        )
    }
}
