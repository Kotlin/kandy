/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.dsl.unit

import org.jetbrains.kotlinx.ggdsl.dsl.source
import org.jetbrains.kotlinx.ggdsl.ir.data.DataSource
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals

internal class SourceTest {
    @Test
    fun testSource() {
        val id = "ID__X"
        val source = source<Int>(id)
        assertEquals(DataSource(id, typeOf<Int>()), source)
    }
}
