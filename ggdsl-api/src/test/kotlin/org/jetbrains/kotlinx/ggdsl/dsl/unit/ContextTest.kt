/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.ggdsl.dsl.impl.*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    private val emptyDataset = DataFrame.Empty
    @Test
    fun testPoints() {
        val context = DataFramePlotContext(emptyDataset).apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    0,
                    POINT,
                    emptyMap(),
                    emptyMap(),
                )
            ),
            context.layers
        )
    }

    @Test
    fun testLine() {
        val context = DataFramePlotContext(emptyDataset).apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    0,
                    LINE,
                    emptyMap(),
                    emptyMap(),
                )
            ),
            context.layers
        )
    }

    @Test
    fun testBars() {
        val context = DataFramePlotContext(emptyDataset).apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    0,
                    BAR,
                    emptyMap(),
                    emptyMap(),
                )
            ),
            context.layers
        )
    }

    @Test
    fun testPlotEmpty() {
        val plot = plot(emptyDataset.toMap()) { }
        assertEquals(
            Plot(
                listOf(NamedData(emptyDataset)),
                listOf(),
                emptyMap(),
                emptyMap(),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }
}