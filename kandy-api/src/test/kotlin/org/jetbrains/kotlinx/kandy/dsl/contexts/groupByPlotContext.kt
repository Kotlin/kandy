package org.jetbrains.kotlinx.kandy.dsl.contexts

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.getColumn
import org.jetbrains.kotlinx.dataframe.api.groupBy
import org.jetbrains.kotlinx.kandy.dsl.internal.GroupByPlotContext
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
}