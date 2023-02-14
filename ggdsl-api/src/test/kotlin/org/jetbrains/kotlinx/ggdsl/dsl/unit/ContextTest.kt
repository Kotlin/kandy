/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.internal.NamedDataPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.data.NamedData
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    private val emptyDataset = NamedData(DataFrame.Empty)
    @Test
    fun testPoints() {
        val context = NamedDataPlotContext(emptyDataset).apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    null,
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
        val context = NamedDataPlotContext(emptyDataset).apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    null,
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
        val context = NamedDataPlotContext(emptyDataset).apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    null,
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
        val plot = plot(emptyDataset) { }
        assertEquals(
            Plot(
                emptyDataset,
                listOf(),
                emptyMap(),
                emptyMap(),
                emptyMap()
            ),
            plot
        )
    }
}