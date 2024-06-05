/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders

import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotBuilder
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
import kotlin.test.assertFailsWith

class DataFramePlotBuilderTest {
    @Test
    fun `test initial DataFrame is set correctly`() {
        val dataFrame = dataFrameOf("name", "age")(
            "Alice", 15,
            "Bob", 20,
            "Charlie", 100
        )

        val plotBuilder = DataFramePlotBuilder(dataFrame)

        assertEquals(1, plotBuilder.datasetHandlers.size)
        assertEquals(dataFrame, plotBuilder.datasetHandlers[0].initialNamedData.dataFrame)
    }

    @Test
    fun `test adding layer`() {
        val dataFrame = dataFrameOf("name", "age")(
            "Alice", 15,
            "Bob", 20,
            "Charlie", 100
        )
        val builder = DataFramePlotBuilder(dataFrame)
        val layerBuilder = mockk<LayerBuilderImpl>(relaxed = true)

        builder.createLayer(layerBuilder, {})

        assertEquals(1, builder.layers.size)
    }

    @Test
    fun `test get columns with selector`() {
        val nameColumn: DataColumn<String> = DataColumn.create("name", listOf("Alice", "Bob", "Charlie"))
        val ageColumn: DataColumn<Int> = DataColumn.create("age", listOf(15, 20, 100))

        val dataFrame = dataFrameOf(nameColumn, ageColumn)

        val name by column<String>("name")
        val age by column<Int>("age")

        val builder = DataFramePlotBuilder(dataFrame)

        val actualNameColumn = builder.columns { name }
        val actualAgeColumn = builder.columns { age }
        val actualColumns = builder.columns { name and age }

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

        val builder = DataFramePlotBuilder(dataFrame)

        builder.groupBy("a", "b") {
            this.createLayer(mockk(relaxed = true), {})
            this.createLayer(mockk(relaxed = true), {})
        }

        assertEquals(2, builder.layers.size)
        assertEquals(2, builder.datasetHandlers.size)
        assertEquals(GroupedData::class, builder.datasetHandlers[1].initialDataset::class)
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

        val builder = DataFramePlotBuilder(dataFrame)

        builder.groupBy(b, c) {
            this.createLayer(mockk(relaxed = true), {})
            this.createLayer(mockk(relaxed = true), {})
        }

        assertEquals(2, builder.layers.size)
        assertEquals(2, builder.datasetHandlers.size)
        assertEquals(GroupedData::class, builder.datasetHandlers[1].initialDataset::class)
    }

    @Test
    fun `test empty plot`() {
        val emptyMap = emptyMap<String, List<Any>>()
        assertFailsWith<IllegalStateException>("No layers in plot") {
            emptyMap.plot {}
        }
    }

    @Test
    fun `test plot with map`() {
        val map = mapOf("A" to listOf("a", "b", "c"), "B" to listOf(1, 2, 3))

        //assertEquals(map.plot {}, plot(map) {})

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

        val plot1 = map.plot {
            createLayer(layerBuilder, {})
        }
        val plot2 = plot(map) {
            createLayer(layerBuilder, {})
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

       // assertEquals(dataFrame.plot {}, plot(dataFrame) {})

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

        val plot1 = dataFrame.plot {
            createLayer(layerBuilder, {})
        }
        val plot2 = plot(dataFrame) {
            createLayer(layerBuilder, {})
        }

        assertEquals(1, plot1.layers.size)
        assertEquals(1, plot2.layers.size)
        assertEquals(plot1, plot2)
    }
}