package org.jetbrains.kotlinx.kandy.dsl.contexts

import io.mockk.mockk
import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.kandy.dsl.internal.DataFramePlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
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
        val geom = mockk<Geom>()

        context.addLayer(layerContext, geom)

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
            this.addLayer(mockk(relaxed = true), geom = mockk())
            this.addLayer(mockk(relaxed = true), geom = mockk())
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
            this.addLayer(mockk(relaxed = true), geom = mockk())
            this.addLayer(mockk(relaxed = true), geom = mockk())
        }

        assertEquals(2, context.layers.size)
        assertEquals(2, context.datasetHandlers.size)
        assertEquals(GroupedData::class, context.datasetHandlers[1].initialDataset::class)
    }
}