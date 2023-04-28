package org.jetbrains.kotlinx.kandy.dsl.unit.internal

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.kandy.dsl.internal.DatasetHandler
import org.jetbrains.kotlinx.kandy.ir.data.GroupedData
import org.jetbrains.kotlinx.kandy.ir.data.NamedData
import org.jetbrains.kotlinx.kandy.ir.data.TableData
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

internal class DatasetHandlerTest {

    @Test
    fun testNamedData() {
        val columnNumbers = listOf(12, 34, 56, 78, 90).toColumn("numbers")
        val columnType = listOf("a", "b", "a", "c", "b").toColumn("TYPE")
        val columnCond = listOf(true, false, false, true, true).toColumn("cond")
        val dataFrame = dataFrameOf(columnNumbers, columnType, columnCond)

        val handler = DatasetHandler(NamedData(dataFrame))
        assertEquals(DataFrame.Empty, handler.buffer)
        assertEquals(NamedData(DataFrame.Empty), handler.data())

        // test simple take
        val colTypeIDAfterTake = handler.takeColumn(columnType.name())
        assertEquals(dataFrameOf(columnType), handler.buffer)
        assertEquals(columnType.name(), colTypeIDAfterTake)

        // test add after take
        val colTypeIDAfterAdd = handler.addColumn(columnType)
        assertEquals(dataFrameOf(columnType), handler.buffer)
        assertEquals(columnType.name(), colTypeIDAfterAdd)

        assertEquals(NamedData(dataFrameOf(columnType)), handler.data())

        // test simple add
        val colNumbersIDAfterAdd = handler.addColumn(columnNumbers)
        assertEquals(dataFrameOf(columnType, columnNumbers), handler.buffer)
        assertEquals(columnNumbers.name(), colNumbersIDAfterAdd)

        // test take after add
        val colNumbersIDAfterTake = handler.takeColumn(columnNumbers.name())
        assertEquals(dataFrameOf(columnType, columnNumbers), handler.buffer)
        assertEquals(columnNumbers.name(), colNumbersIDAfterTake)

        // test add after add
        val colNumbersIDAfterRepeatAdd = handler.addColumn(columnNumbers)
        assertEquals(dataFrameOf(columnType, columnNumbers), handler.buffer)
        assertEquals(columnNumbers.name(), colNumbersIDAfterRepeatAdd)

        assertEquals(NamedData(dataFrameOf(columnType, columnNumbers)), handler.data())

        // test list add
        val colCondIDAfterAdd = handler.addColumn(columnCond.values().toList(), columnCond.name())
        assertEquals(dataFrameOf(columnType, columnNumbers, columnCond), handler.buffer)
        assertEquals(columnCond.name(), colCondIDAfterAdd)

        val columnNumbers2 = columnNumbers.map { it + 1 }

        // test add same with same name and different values
        val columnNumbers2IDAfterAdd = handler.addColumn(columnNumbers2)
        assertEquals(dataFrameOf(columnType, columnNumbers,  columnCond, columnNumbers2.rename("numbers*")), handler.buffer)
        assertEquals("numbers*", columnNumbers2IDAfterAdd)

        // test take
        assertFailsWith<IllegalStateException>(message = "invalid column id") {
            handler.takeColumn("column no 6")
        }

    }

    
    private fun assertEqualsContent(expected: GroupBy<*, *>, actual: TableData) {
        assertIs<GroupedData>(actual)
        assertEquals(expected.keys.columnNames(), actual.groupBy.keys.columnNames())
        assertEquals(expected.groups.concat(), actual.groupBy.groups.concat())
    }

    @Test
    fun testGroupedData() {
        val columnNumbers = listOf(12, 34, 56, 78, 90).toColumn("numbers")
        val columnType = listOf("a", "b", "a", "c", "b").toColumn("TYPE")
        val columnCond = listOf(true, false, false, true, true).toColumn("cond")
        val groupKey = "TYPE"
        val dataFrame = dataFrameOf(columnNumbers, columnType, columnCond).groupBy(groupKey)
        val groupedData = GroupedData(dataFrame)

        val internalColumnNumbers = groupedData.origin.dataFrame["numbers"]
        val internalColumnType = groupedData.origin.dataFrame["TYPE"]
        val internalColumnCond = groupedData.origin.dataFrame["cond"]

        val handler = DatasetHandler(groupedData)
        assertEquals(dataFrameOf(internalColumnType), handler.buffer, groupKey)

        // test simple take
        val colTypeIDAfterTake = handler.takeColumn(columnType.name())
        assertEquals(dataFrameOf(internalColumnType), handler.buffer, groupKey)
        assertEquals(columnType.name(), colTypeIDAfterTake)

        // test add after take
        val colTypeIDAfterAdd = handler.addColumn(internalColumnType)
        assertEquals(dataFrameOf(internalColumnType), handler.buffer, groupKey)
        assertEquals(columnType.name(), colTypeIDAfterAdd)

        assertEqualsContent(dataFrameOf(internalColumnType).groupBy(groupKey), handler.data())

        // test simple add
        val colNumbersIDAfterAdd = handler.addColumn(internalColumnNumbers)
        assertEquals(dataFrameOf(internalColumnType, internalColumnNumbers), handler.buffer)
        assertEquals(columnNumbers.name(), colNumbersIDAfterAdd)

        // test take after add
        val colNumbersIDAfterTake = handler.takeColumn(columnNumbers.name())
        assertEquals(dataFrameOf(internalColumnType, internalColumnNumbers), handler.buffer)
        assertEquals(columnNumbers.name(), colNumbersIDAfterTake)

        // test add after add
        val colNumbersIDAfterRepeatAdd = handler.addColumn(internalColumnNumbers)
        assertEquals(dataFrameOf(internalColumnType, internalColumnNumbers), handler.buffer)
        assertEquals(columnNumbers.name(), colNumbersIDAfterRepeatAdd)

        assertEqualsContent(dataFrameOf(internalColumnType, internalColumnNumbers).groupBy(groupKey), handler.data())

        // test list add
        val colCondIDAfterAdd = handler.addColumn(columnCond.values().toList(), columnCond.name())
        assertEquals(dataFrameOf(internalColumnType, internalColumnNumbers, internalColumnCond), handler.buffer)
        assertEquals(columnCond.name(), colCondIDAfterAdd)

        val columnNumbers2 = columnNumbers.map { it + 1 }

        // test add same with same name and different values
        val columnNumbers2IDAfterAdd = handler.addColumn(columnNumbers2)
        assertEquals(dataFrameOf(internalColumnType, internalColumnNumbers, internalColumnCond, columnNumbers2.rename("numbers*")), handler.buffer)
        assertEquals("numbers*", columnNumbers2IDAfterAdd)

        // test take
        assertFailsWith<IllegalStateException>(message = "invalid column id") {
            handler.takeColumn("column no 6")
        }

    }

}