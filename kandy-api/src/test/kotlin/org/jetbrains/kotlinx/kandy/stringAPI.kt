/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy

import org.jetbrains.kotlinx.kandy.dsl.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

internal class StringAPITest {
    @Test
    fun `test invoke String`() {
        val columnName = "column_name"
        val columnReference = columnName<Int>()
        assertEquals(columnReference.name(), columnName)
    }
}
