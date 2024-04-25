/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.getColumn
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.GroupByPlotBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
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

class GroupByMultiMultiLayerPlotBuilderInterfaceImplTest {
    private val a = DataColumn.create("a", listOf(1, 4))
    private val b = DataColumn.create("b", listOf(2, 5))
    private val c = DataColumn.create("c", listOf(3, 6))
    private val dataFrame = dataFrameOf(a, b, c)

    @Test
    fun `test columns`() {
        val builder = GroupByPlotBuilder(dataFrame.groupBy("a", "c"))

        assertEquals(listOf(a, b, c), builder.columns())
    }

    @Test
    fun `test get`() {
        val builder = GroupByPlotBuilder(dataFrame.groupBy("a"))
        assertEquals(a, builder[a])
        assertEquals(a, builder.getColumn(a))
        assertEquals(a, builder["a"])
        assertEquals(a, builder.getColumn("a"))
        assertEquals(b, builder["b"])
    }

    @Test
    fun `test columnsCount`() {
        val builder = GroupByPlotBuilder(dataFrame.groupBy("a", "b"))
        assertEquals(3, builder.columnsCount())
    }

    @Test
    fun `test containsColumn`() {
        val builder = GroupByPlotBuilder(dataFrame.groupBy("a", "c"))
        assertTrue(builder.containsColumn("a"))
        assertTrue(builder.containsColumn("b"))
        assertTrue(builder.containsColumn("c"))
        assertFalse(builder.containsColumn("d"))
    }

    @Test
    fun `test getColumnIndex`() {
        val builder = GroupByPlotBuilder(dataFrame.groupBy("a", "b"))

        assertEquals(0, builder.getColumnIndex("a"))
        assertEquals(1, builder.getColumnIndex("b"))
        assertEquals(2, builder.getColumnIndex("c"))
    }

    @Test
    fun `test getColumnOrNull`() {
        val builder = GroupByPlotBuilder(dataFrame.groupBy("c"))
        assertEquals(b, builder.getColumnOrNull(1))
        assertEquals(a, builder.getColumnOrNull("a"))
        assertEquals(c, builder.getColumnOrNull(c))
        assertEquals(b, builder.getColumnOrNull { b })
    }

    @Test
    fun `test empty plot with grouped DataFrame`() {
        // TODO handle error
        /*val emptyPlot1 = dataFrame.groupBy("a").plot {}
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
        )*/

        val geom = mockk<Geom>()
        val mockLayer = Layer(
            datasetIndex = 0, geom = geom,
            mappings = mapOf(Aes("a") to mockk<Mapping>()),
            settings = mapOf(Aes("a") to mockk<Setting>()),
            features = emptyMap(), freeScales = emptyMap(), inheritsBindings = true
        )
        val layerBuilder = object : LayerBuilderImpl(mockk<LayerCreatorScope>(relaxed = true)) {
            override val geom: Geom = mockk()
            override val requiredAes: Set<Aes> = emptySet<Aes>()
            override fun toLayer() = mockLayer
        }

        val plot1 = dataFrame.groupBy("b", "c").plot {
            createLayer(layerBuilder, {})
        }
        val plot2 = plot(dataFrame.groupBy("b", "c")) {
            createLayer(layerBuilder, {})
        }

        assertEquals(1, plot1.layers.size)
        assertEquals(1, plot2.layers.size)
        assertEquals(mockLayer, plot1.layers.first())
        assertEquals(mockLayer, plot2.layers.first())
    }
}