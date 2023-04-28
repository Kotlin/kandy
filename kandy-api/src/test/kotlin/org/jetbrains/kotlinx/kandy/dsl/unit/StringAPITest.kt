package org.jetbrains.kotlinx.kandy.dsl.unit

import org.jetbrains.kotlinx.kandy.dsl.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StringAPITest {

    @Test
    fun testStringInvoke(){
        val columnName = "column_name"
        val columnReference = columnName<Int>()
        assertEquals(columnReference.name(), columnName)
    }
}
