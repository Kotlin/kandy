/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.checkInRange
import org.jetbrains.kotlinx.kandy.dsl.internal.checkRequiredAes
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ValidationTests {

    @Test
    fun `test value in range`() {
        val value = 5
        val range = 1..10
        val aes = Aes("Test")

        checkInRange(aes, value, range)
    }

    @Test
    fun `test value out of range`() {
        val value = 15
        val range = 1..10
        val aes = Aes("Test")

        val exception = assertFailsWith<IllegalArgumentException> {
            checkInRange(aes, value, range)
        }
        assertEquals(
            "Value `$value` of `${aes.name}` is outside the range [${range.first}, ${range.last}].",
            exception.message
        )
    }

    @Test
    fun `test required Aes from mapping and settings in layer`() {
        val requiredAes = setOf(Aes("A"), Aes("B"))
        val layerContext = mockk<LayerContextInterface>()
        every { layerContext.bindingCollector.mappings.keys } returns mutableSetOf(Aes("A"))
        every { layerContext.bindingCollector.settings.keys } returns mutableSetOf(Aes("B"))
        val plotContext = null

        checkRequiredAes(requiredAes, layerContext, plotContext)
    }

    @Test
    fun `test required Aes from mapping and settings in layerContext and plotContext`() {
        val requiredAes = setOf(Aes("A"), Aes("B"), Aes("C"), Aes("D"))
        val layerContext = mockk<LayerContextInterface>()
        every { layerContext.bindingCollector.mappings.keys } returns mutableSetOf(Aes("A"))
        every { layerContext.bindingCollector.settings.keys } returns mutableSetOf(Aes("B"))
        val plotContext: PlotContext = mockk<PlotContext>()
        every { plotContext.bindingCollector.mappings.keys } returns mutableSetOf(Aes("C"))
        every { plotContext.bindingCollector.settings.keys } returns mutableSetOf(Aes("D"))

        checkRequiredAes(requiredAes, layerContext, plotContext)
    }

    @Test
    fun `test required Aes not assigned`() {
        val requiredAes = setOf(Aes("A"), Aes("B"), Aes("C"))
        val layerContext = mockk<LayerContextInterface>()
        every { layerContext.bindingCollector.mappings.keys } returns mutableSetOf(Aes("A"))
        every { layerContext.bindingCollector.settings.keys } returns mutableSetOf(Aes("B"))
        val plotContext: PlotContext? = null

        val exception = assertFailsWith<IllegalArgumentException> {
            checkRequiredAes(requiredAes, layerContext, plotContext)
        }

        assertEquals("`C` is not assigned.", exception.message)
    }
}
