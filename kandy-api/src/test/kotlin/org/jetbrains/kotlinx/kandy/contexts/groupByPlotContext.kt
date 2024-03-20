/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.getColumn
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.GroupByPlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GroupByPlotContextTest {
    private val a = DataColumn.create("a", listOf(1, 4))
    private val b = DataColumn.create("b", listOf(2, 5))
    private val c = DataColumn.create("c", listOf(3, 6))
    private val dataFrame = dataFrameOf(a, b, c)

    @Test
    fun `test columns`() {
        val context = GroupByPlotContext(dataFrame.groupBy("a", "c"))

        assertEquals(listOf(a, b, c), context.columns())
    }

    @Test
    fun `test get`() {
        val context = GroupByPlotContext(dataFrame.groupBy("a"))
        assertEquals(a, context[a])
        assertEquals(a, context.getColumn(a))
        assertEquals(a, context["a"])
        assertEquals(a, context.getColumn("a"))
        assertEquals(b, context["b"])
    }

    @Test
    fun `test columnsCount`() {
        val context = GroupByPlotContext(dataFrame.groupBy("a", "b"))
        assertEquals(3, context.columnsCount())
    }

    @Test
    fun `test containsColumn`() {
        val context = GroupByPlotContext(dataFrame.groupBy("a", "c"))
        assertTrue(context.containsColumn("a"))
        assertTrue(context.containsColumn("b"))
        assertTrue(context.containsColumn("c"))
        assertFalse(context.containsColumn("d"))
    }

    @Test
    fun `test getColumnIndex`() {
        val context = GroupByPlotContext(dataFrame.groupBy("a", "b"))

        assertEquals(0, context.getColumnIndex("a"))
        assertEquals(1, context.getColumnIndex("b"))
        assertEquals(2, context.getColumnIndex("c"))
    }

    @Test
    fun `test getColumnOrNull`() {
        val context = GroupByPlotContext(dataFrame.groupBy("c"))
        assertEquals(b, context.getColumnOrNull(1))
        assertEquals(a, context.getColumnOrNull("a"))
        assertEquals(c, context.getColumnOrNull(c))
        assertEquals(b, context.getColumnOrNull { b })
    }

    @Test
    fun `test empty plot with grouped DataFrame`() {
        val emptyPlot1 = dataFrame.groupBy("a").plot {}
        val emptyPlot2 = plot(dataFrame.groupBy("a")) {}

        assertEquals(
            (emptyPlot1.datasets.first() as GroupedData).dataFrame,
            (emptyPlot2.datasets.first() as GroupedData).dataFrame
        )
        assertEquals(
            (emptyPlot1.datasets.first() as GroupedData).keys,
            (emptyPlot2.datasets.first() as GroupedData).keys
        )
        assertEquals(
            (emptyPlot1.datasets.first() as GroupedData).groupBy.groups,
            (emptyPlot2.datasets.first() as GroupedData).groupBy.groups
        )

        val geom = mockk<Geom>()
        val mockLayer = Layer(
            datasetIndex = 0, geom = geom,
            mappings = mapOf(Aes("a") to mockk<Mapping>()),
            settings = mapOf(Aes("a") to mockk<Setting>()),
            features = emptyMap(), freeScales = emptyMap(), inheritsBindings = true
        )
        val layerContext = mockk<LayerContextInterface> {
            every { requiredAes } returns emptySet()
            every { bindingCollector } returns BindingCollector()
            every { toLayer(true) } returns mockLayer
        }

        val plot1 = dataFrame.groupBy("b", "c").plot {
            addLayer(layerContext)
        }
        val plot2 = plot(dataFrame.groupBy("b", "c")) {
            addLayer(layerContext)
        }

        assertEquals(1, plot1.layers.size)
        assertEquals(1, plot2.layers.size)
        assertEquals(mockLayer, plot1.layers.first())
        assertEquals(mockLayer, plot2.layers.first())
    }
}