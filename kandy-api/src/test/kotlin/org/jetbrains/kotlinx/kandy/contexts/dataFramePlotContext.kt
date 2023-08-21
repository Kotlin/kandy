/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.emptyDataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.BindingCollector
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Layer
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import org.jetbrains.kotlinx.kandy.ir.bindings.Mapping
import org.jetbrains.kotlinx.kandy.ir.bindings.Setting
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertEquals

class DataFramePlotContextTest {
    @Test
    fun `test initial DataFrame is set correctly`() {
        val dataFrame = dataFrameOf("name", "age")(
            "Alice", 15,
            "Bob", 20,
            "Charlie", 100
        )

        val context = DataFramePlotContext(dataFrame)

        assertEquals(1, context.datasetHandlers.size)
        assertEquals(dataFrame, context.datasetHandlers[0].initialNamedData.dataFrame)
    }

    @Test
    fun `test adding layer`() {
        val dataFrame = dataFrameOf("name", "age")(
            "Alice", 15,
            "Bob", 20,
            "Charlie", 100
        )
        val context = DataFramePlotContext(dataFrame)
        val layerContext = mockk<LayerContextInterface>(relaxed = true)

        context.addLayer(layerContext)

        assertEquals(1, context.layers.size)
    }

    @Test
    fun `test get columns with selector`() {
        val nameColumn: DataColumn<String> = DataColumn.create("name", listOf("Alice", "Bob", "Charlie"))
        val ageColumn: DataColumn<Int> = DataColumn.create("age", listOf(15, 20, 100))

        val dataFrame = dataFrameOf(nameColumn, ageColumn)

        val name by column<String>("name")
        val age by column<Int>("age")

        val context = DataFramePlotContext(dataFrame)

        val actualNameColumn = context.columns { name }
        val actualAgeColumn = context.columns { age }
        val actualColumns = context.columns { name and age }

        assertEquals(nameColumn, actualNameColumn.first())
        assertEquals(ageColumn, actualAgeColumn.first())
        assertEquals(listOf(nameColumn, ageColumn), actualColumns)
    }

    @Test
    fun `test groupBy with strings`() {
        val dataFrame = dataFrameOf(
            "a", "b", "c"
        )(
            1, 2, 3,
            4, 5, 6,
        )

        val context = DataFramePlotContext(dataFrame)

        context.groupBy("a", "b") {
            this.addLayer(mockk(relaxed = true))
            this.addLayer(mockk(relaxed = true))
        }

        assertEquals(2, context.layers.size)
        assertEquals(2, context.datasetHandlers.size)
        assertEquals(GroupedData::class, context.datasetHandlers[1].initialDataset::class)
    }

    @Test
    fun `test groupBy with columnRef`() {
        val dataFrame = dataFrameOf(
            "a", "b", "c"
        )(
            1, 2, 3,
            4, 5, 6,
        )
        val b by column<Int>("b")
        val c by column<Int>("c")

        val context = DataFramePlotContext(dataFrame)

        context.groupBy(b, c) {
            this.addLayer(mockk(relaxed = true))
            this.addLayer(mockk(relaxed = true))
        }

        assertEquals(2, context.layers.size)
        assertEquals(2, context.datasetHandlers.size)
        assertEquals(GroupedData::class, context.datasetHandlers[1].initialDataset::class)
    }

    @Test
    fun `test empty plot`() {
        val emptyMap = emptyMap<String, List<Any>>()
        val plot = emptyMap.plot {}
        val expected = Plot(
            datasets = mutableListOf(NamedData(emptyDataFrame<Any>())), layers = emptyList(),
            globalMappings = emptyMap(), globalSettings = emptyMap(), features = emptyMap(), freeScales = emptyMap()
        )
        assertEquals(expected, plot)
    }

    @Test
    fun `test plot with map`() {
        val map = mapOf("A" to listOf("a", "b", "c"), "B" to listOf(1, 2, 3))

        assertEquals(map.plot {}, plot(map) {})

        val geom = mockk<Geom>()
        val mockLayer = Layer(
            datasetIndex = 0, geom = geom,
            mappings = mapOf(AesName("a") to mockk<Mapping>()),
            settings = mapOf(AesName("a") to mockk<Setting>()),
            features = emptyMap(), freeScales = emptyMap(), inheritsBindings = true
        )
        val layerContext = mockk<LayerContextInterface> {
            every { requiredAes } returns emptySet()
            every { bindingCollector } returns BindingCollector()
            every { toLayer(true) } returns mockLayer
        }

        val plot1 = map.plot {
            addLayer(layerContext)
        }
        val plot2 = plot(map) {
            addLayer(layerContext)
        }

        assertEquals(1, plot1.layers.size)
        assertEquals(1, plot2.layers.size)
        assertEquals(plot1, plot2)
    }

    @Test
    fun `test plot with dataframe`() {
        val dataFrame = dataFrameOf(
            "A" to listOf("a", "b", "c"),
            "B" to listOf(1, 2, 3)
        )

        assertEquals(dataFrame.plot {}, plot(dataFrame) {})

        val geom = mockk<Geom>()
        val mockLayer = Layer(
            datasetIndex = 0, geom = geom,
            mappings = mapOf(AesName("a") to mockk<Mapping>()),
            settings = mapOf(AesName("a") to mockk<Setting>()),
            features = emptyMap(), freeScales = emptyMap(), inheritsBindings = true
        )
        val layerContext = mockk<LayerContextInterface> {
            every { requiredAes } returns emptySet()
            every { bindingCollector } returns BindingCollector()
            every { toLayer(true) } returns mockLayer
        }

        val plot1 = dataFrame.plot {
            addLayer(layerContext)
        }
        val plot2 = plot(dataFrame) {
            addLayer(layerContext)
        }

        assertEquals(1, plot1.layers.size)
        assertEquals(1, plot2.layers.size)
        assertEquals(plot1, plot2)
    }
}