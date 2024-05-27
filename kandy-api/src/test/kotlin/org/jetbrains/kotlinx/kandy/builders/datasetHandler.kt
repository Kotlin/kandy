/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.builders

import org.jetbrains.kotlinx.dataframe.AnyCol
import org.jetbrains.kotlinx.dataframe.AnyFrame
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetBuilder
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.GroupedData
import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import kotlin.test.*

class DatasetBuilderTest {
    private val numbers = listOf(12, 34, 56, 78, 90).toColumn("numbers")
    private val type = listOf("a", "b", "a", "c", "b").toColumn("TYPE")
    private val cond = listOf(true, false, false, true, true).toColumn("cond")
    private val dataFrame: AnyFrame = dataFrameOf(numbers, type, cond)

    private val groupedDf: GroupBy<Any?, Any?> = dataFrameOf(numbers, type, cond).groupBy(type)
    private lateinit var internalNumbers: AnyCol
    private lateinit var internalType: AnyCol
    private lateinit var internalCond: AnyCol

    private lateinit var handler: DatasetBuilder
    private lateinit var handlerWithGrouped: DatasetBuilder

    @BeforeTest
    fun setup() {
        handler = DatasetBuilder(NamedData(dataFrame))

        val groupedData = GroupedData(groupedDf)
        internalNumbers = groupedData.dataFrame["numbers"]
        internalType = groupedData.dataFrame["TYPE"]
        internalCond = groupedData.dataFrame["cond"]
        handlerWithGrouped = DatasetBuilder(groupedData)
    }

    @Test
    fun `test initial NamedData`() {
        val initialDataset = NamedData(emptyDataFrame<Any>())
        val handler = DatasetBuilder(initialDataset)

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
        val handler = DatasetBuilder(initialDataset)

        assertEquals(initialDataset.dataFrame, handler.initialNamedData.dataFrame)
    }

    @Test
    fun `test add column with list values`() {
        val initialDataset = NamedData(emptyDataFrame<Any>())
        val handler = DatasetBuilder(initialDataset)
        val values = listOf(1, 2, 3)
        val name = "new_column"

        val columnName = handler.addColumn(values, name)

        assertEquals(name, columnName)
        assertEquals(values, handler.buffer[name].values())
    }

    @Test
    fun `test nameData with regular dataframe`() {
        assertEquals(DataFrame.Empty, handler.buffer)
        assertEquals(NamedData(DataFrame.Empty), handler.build())
    }

    @Test
    fun `test take column`() {
        val colTypeIDAfterTake = handler.takeColumn(type.name())

        assertEquals(dataFrameOf(type), handler.buffer)
        assertEquals(type.name(), colTypeIDAfterTake)
    }

    @Test
    fun `test add column`() {
        handler.addColumn(type)
        val colNumbersIDAfterAdd = handler.addColumn(numbers)

        assertEquals(dataFrameOf(type, numbers), handler.buffer)
        assertEquals(numbers.name(), colNumbersIDAfterAdd)
    }

    @Test
    fun `test add column after take`() {
        val dfOfTypeColumn = dataFrameOf(type)

        handler.takeColumn(type.name())
        val colTypeIDAfterAdd = handler.addColumn(type)

        assertEquals(dfOfTypeColumn, handler.buffer)
        assertEquals(type.name(), colTypeIDAfterAdd)
        assertEquals(NamedData(dfOfTypeColumn), handler.build())
    }

    @Test
    fun `test take column after add`() {
        handler.addColumn(numbers)
        val colNumbersIDAfterTake = handler.takeColumn(numbers.name())

        assertEquals(dataFrameOf(numbers), handler.buffer)
        assertEquals(numbers.name(), colNumbersIDAfterTake)
    }

    @Test
    fun `test add list values`() {
        val colCondIDAfterAdd = handler.addColumn(cond.values().toList(), cond.name())

        assertEquals(dataFrameOf(cond), handler.buffer)
        assertEquals(cond.name(), colCondIDAfterAdd)
    }

    @Test
    fun `test add column with same name`() {
        val numbers2 = numbers.map { it + 1 }

        handler.addColumn(numbers)
        val columnNumbers2IDAfterAdd = handler.addColumn(numbers2)

        assertEquals(dataFrameOf(numbers, numbers2.rename("numbers*")), handler.buffer)
        assertEquals("numbers*", columnNumbers2IDAfterAdd)
    }

    @Test
    fun `test take non-exist column`() {
        val name = "columnNonExist"
        assertFailsWith<IllegalStateException>(message = "invalid column id: $name") {
            handler.takeColumn(name)
        }
    }

    @Test
    fun `test GroupedData`() {
        assertEquals(dataFrameOf(internalType), handlerWithGrouped.buffer)
    }

    @Test
    fun `test take column with GroupedData`() {
        val colTypeIDAfterTake = handlerWithGrouped.takeColumn(type.name())

        assertEquals(dataFrameOf(internalType), handlerWithGrouped.buffer)
        assertEquals(type.name(), colTypeIDAfterTake)
    }

    @Test
    fun `test add column with GroupedData`() {
        val colNumbersIDAfterAdd = handlerWithGrouped.addColumn(internalNumbers)

        assertEquals(dataFrameOf(internalType, internalNumbers), handlerWithGrouped.buffer)
        assertEquals(numbers.name(), colNumbersIDAfterAdd)
    }

    @Test
    fun `test add column after take with GroupedData`() {
        handlerWithGrouped.takeColumn(type.name())
        val colTypeIDAfterAdd = handlerWithGrouped.addColumn(internalType)

        assertEquals(dataFrameOf(internalType), handlerWithGrouped.buffer)
        assertEquals(type.name(), colTypeIDAfterAdd)

        val expectedDf = dataFrameOf(internalType).groupBy(type)
        val actualDf: TableData = handlerWithGrouped.build()
        assertIs<GroupedData>(actualDf)
        assertEquals(expectedDf.keys.columnNames(), actualDf.groupBy.keys.columnNames())
        assertEquals(expectedDf.groups.concat(), actualDf.groupBy.groups.concat())
    }

    @Test
    fun `test take column after add with GroupedData`() {
        handlerWithGrouped.addColumn(internalNumbers)
        val colNumbersIDAfterTake = handlerWithGrouped.takeColumn(numbers.name())

        assertEquals(dataFrameOf(internalType, internalNumbers), handlerWithGrouped.buffer)
        assertEquals(numbers.name(), colNumbersIDAfterTake)
    }

    @Test
    fun `test add column after add with GroupedData`() {
        handlerWithGrouped.addColumn(internalType)
        handlerWithGrouped.addColumn(internalNumbers)
        val colNumbersIDAfterRepeatAdd = handlerWithGrouped.addColumn(internalNumbers)
        val expectedDf = dataFrameOf(type, numbers).groupBy(type)

        assertEquals(dataFrameOf(internalType, internalNumbers), handlerWithGrouped.buffer)
        assertEquals(numbers.name(), colNumbersIDAfterRepeatAdd)

        val actualDf = handlerWithGrouped.build()
        assertIs<GroupedData>(actualDf)
        assertEquals(expectedDf.keys.columnNames(), actualDf.groupBy.keys.columnNames())
        assertEquals(expectedDf.groups.concat(), actualDf.groupBy.groups.concat())
    }

    @Test
    fun `test add list values with GroupedData`() {
        val colCondIDAfterAdd = handlerWithGrouped.addColumn(cond.values().toList(), cond.name())

        assertEquals(dataFrameOf(internalType, cond), handlerWithGrouped.buffer)
        assertEquals(cond.name(), colCondIDAfterAdd)
    }

    @Test
    fun `test add column with same name and with GroupedData`() {
        val numbers2 = numbers.map { it + 1 }

        handlerWithGrouped.addColumn(numbers)
        val columnNumbers2IDAfterAdd = handlerWithGrouped.addColumn(numbers2)

        assertEquals(dataFrameOf(internalType, numbers, numbers2.rename("numbers*")), handlerWithGrouped.buffer)
        assertEquals("numbers*", columnNumbers2IDAfterAdd)
    }

    @Test
    fun `test take non-exist column with GroupedData`() {
        val name = "columnNonExist"
        assertFailsWith<IllegalStateException>(message = "invalid column id: $name") {
            handlerWithGrouped.takeColumn(name)
        }
    }
}
