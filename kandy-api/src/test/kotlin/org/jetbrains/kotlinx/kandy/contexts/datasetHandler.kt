/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.contexts

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.emptyDataFrame
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import kotlin.test.Test
import kotlin.test.assertEquals

class DatasetHandlerTest {
    @Test
    fun `test initial NamedData`() {
        val initialDataset = NamedData(emptyDataFrame<Any>())
        val handler = DatasetHandler(initialDataset)

        assertEquals(initialDataset, handler.initialNamedData)
    }

    @Test
    fun `test initial GroupedData`() {
        val initialDataset = GroupedData(
            NamedData(
                dataFrameOf("column1", "column2")(
                    "A", 1,
                    "A", 2,
                    "B", 3,
                    "B", 4,
                    "B", 5,
                )
            ), listOf("column1")
        )
        val handler = DatasetHandler(initialDataset)

        assertEquals(initialDataset.origin, handler.initialNamedData)
//        assertEquals(initialDataset.keys, handler.groupKeys)
    }

    // TODO(review referredColumns, and internalAddColumn logic)
    @Test
    fun `test add and take column`() {
        val columnName = "column"
        val initialDataset = NamedData(dataFrameOf(columnName)("a", "b", "c"))
        val handler = DatasetHandler(initialDataset)
        val newColumn = DataColumn.create("new_column", listOf(1, 2, 3))

        assertEquals("column", handler.takeColumn("column"))

        handler.addColumn(newColumn)
        assertEquals(newColumn.name(), handler.takeColumn(newColumn.name())) //TODO
    }

    @Test
    fun `test add column with list values`() {
        val initialDataset = NamedData(emptyDataFrame<Any>())
        val handler = DatasetHandler(initialDataset)
        val values = listOf(1, 2, 3)
        val name = "new_column"

        val columnName = handler.addColumn(values, name)

        assertEquals(name, columnName)
        assertEquals(values, handler.buffer[name].values())
    }
}
