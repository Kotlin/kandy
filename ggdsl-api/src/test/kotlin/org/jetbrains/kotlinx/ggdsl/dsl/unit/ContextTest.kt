/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.ir.Layer
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class ContextTest {
    @Test
    fun testPoints() {
        val context = PlotContext().apply {
            points {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    POINT,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testLine() {
        val context = PlotContext().apply {
            line {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    LINE,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testBars() {
        val context = PlotContext().apply {
            bars {}
        }
        assertContentEquals(
            listOf(
                Layer(
                    mapOf(),
                    BAR,
                    mapOf(),
                    mapOf()
                )
            ),
            context.layers
        )
    }

    @Test
    fun testPlotEmpty() {
        val plot = plot { }
        assertEquals(
            Plot(
                mapOf(),
                listOf(),
                null,
                mapOf()
            ),
            plot
        )
    }
}