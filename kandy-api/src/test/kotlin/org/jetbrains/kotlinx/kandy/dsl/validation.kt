package org.jetbrains.kotlinx.kandy.dsl

import io.mockk.every
import io.mockk.mockk
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContextInterface
import org.jetbrains.kotlinx.kandy.dsl.internal.PlotContext
import org.jetbrains.kotlinx.kandy.dsl.internal.checkInRange
import org.jetbrains.kotlinx.kandy.dsl.internal.checkRequiredAes
import org.jetbrains.kotlinx.kandy.ir.aes.AesName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ValidationTests {

    @Test
    fun `test value in range`() {
        val value = 5
        val range = 1..10
        val aesName = AesName("Test")

        checkInRange(aesName, value, range)
    }

    @Test
    fun `test value out of range`() {
        val value = 15
        val range = 1..10
        val aesName = AesName("Test")

        val exception = assertFailsWith<IllegalArgumentException> {
            checkInRange(aesName, value, range)
        }
        assertEquals(
            "Value `$value` of `${aesName.name}` is outside the range [${range.first}, ${range.last}].",
            exception.message
        )
    }

    @Test
    fun `test required Aes from mapping and settings in layer`() {
        val requiredAes = setOf(AesName("A"), AesName("B"))
        val layerContext = mockk<LayerContextInterface>()
        every { layerContext.bindingCollector.mappings.keys } returns mutableSetOf(AesName("A"))
        every { layerContext.bindingCollector.settings.keys } returns mutableSetOf(AesName("B"))
        val plotContext = null

        checkRequiredAes(requiredAes, layerContext, plotContext)
    }

    @Test
    fun `test required Aes from mapping and settings in layerContext and plotContext`() {
        val requiredAes = setOf(AesName("A"), AesName("B"), AesName("C"), AesName("D"))
        val layerContext = mockk<LayerContextInterface>()
        every { layerContext.bindingCollector.mappings.keys } returns mutableSetOf(AesName("A"))
        every { layerContext.bindingCollector.settings.keys } returns mutableSetOf(AesName("B"))
        val plotContext: PlotContext = mockk<PlotContext>()
        every { plotContext.bindingCollector.mappings.keys } returns mutableSetOf(AesName("C"))
        every { plotContext.bindingCollector.settings.keys } returns mutableSetOf(AesName("D"))

        checkRequiredAes(requiredAes, layerContext, plotContext)
    }

    @Test
    fun `test required Aes not assigned`() {
        val requiredAes = setOf(AesName("A"), AesName("B"), AesName("C"))
        val layerContext = mockk<LayerContextInterface>()
        every { layerContext.bindingCollector.mappings.keys } returns mutableSetOf(AesName("A"))
        every { layerContext.bindingCollector.settings.keys } returns mutableSetOf(AesName("B"))
        val plotContext: PlotContext? = null

        val exception = assertFailsWith<IllegalArgumentException> {
            checkRequiredAes(requiredAes, layerContext, plotContext)
        }

        assertEquals("`C` is not assigned.", exception.message)
    }
}
