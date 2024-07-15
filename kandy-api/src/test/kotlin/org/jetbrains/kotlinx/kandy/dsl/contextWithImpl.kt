/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.toMap
import org.jetbrains.kotlinx.kandy.dsl.impl.*
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.DataFramePlotBuilder
import org.jetbrains.kotlinx.kandy.ir.Layer
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith

class BuilderImplTest {
    private val emptyDataset = DataFrame.Empty

    @Test
    fun testPoints() {
        val builder = DataFramePlotBuilder(emptyDataset).apply {
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
            builder.layers
        )
    }

    @Test
    fun testLine() {
        val builder = DataFramePlotBuilder(emptyDataset).apply {
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
            builder.layers
        )
    }

    @Test
    fun testBars() {
        val builder = DataFramePlotBuilder(emptyDataset).apply {
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
            builder.layers
        )
    }

    @Test
    fun testPlotEmpty() {
        assertFailsWith<IllegalStateException>("No layers in plot") {
            plot(emptyDataset.toMap()) { }
        }
    }
}