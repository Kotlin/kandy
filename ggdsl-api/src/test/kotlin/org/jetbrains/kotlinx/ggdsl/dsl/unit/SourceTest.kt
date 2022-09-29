package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.columnPointer
import org.jetbrains.kotlinx.ggdsl.ir.data.ColumnPointer
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SourceTest {
    @Test
    fun testSource() {
        val id = "ID__X"
        val source = columnPointer<Int>(id)
        assertEquals(ColumnPointer<Int>(id), source)
    }
}
