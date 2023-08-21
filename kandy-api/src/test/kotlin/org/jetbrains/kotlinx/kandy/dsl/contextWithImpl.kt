/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.kandy.dsl.impl.*
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ContextImplTest {
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