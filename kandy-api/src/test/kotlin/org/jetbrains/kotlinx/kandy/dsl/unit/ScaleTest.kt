/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.dsl.unit

import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.categoricalPos
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.continuousPos
import org.jetbrains.kotlinx.kandy.dsl.impl.CommonNonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.dsl.impl.CommonNonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.dsl.impl.CommonPositionalMappingParameters
import org.jetbrains.kotlinx.kandy.dsl.impl.CommonPositionalMappingParametersContinuous
import org.jetbrains.kotlinx.kandy.ir.scale.*
import org.jetbrains.kotlinx.kandy.util.color.Color
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ScaleTest {

    @Test
    fun testContinuousPosDefault() {
        val expectedScale = PositionalContinuousScale<Float>(null, null, null, null)
        assertEquals(expectedScale, Scale.continuousPos<Float>())
        assertEquals(expectedScale, CommonPositionalMappingParametersContinuous<Float>().continuous())
    }

    @Test
    fun testContinuousPos() {
        val limits = -5..19
        val expectedScale = PositionalContinuousScale<Int>(limits.first, limits.last, null, null)
        assertEquals(expectedScale, Scale.continuousPos<Int>(limits))
        assertEquals(expectedScale, CommonPositionalMappingParametersContinuous<Int>().continuous(limits))
    }

    @Test
    fun testCategoricalPosDefault() {
        val expectedScale = PositionalCategoricalScale<Float>(null)
        assertEquals(expectedScale, Scale.categoricalPos<Float>())
        assertEquals(expectedScale, CommonPositionalMappingParameters<Float>().categorical())
    }

    @Test
    fun testCategoricalPos() {
        val categories = listOf("a", "b", "CCC", "123")
        val expectedScale = PositionalCategoricalScale<String>(categories)
        assertEquals(expectedScale, Scale.categoricalPos<String>(categories))
        assertEquals(expectedScale, CommonPositionalMappingParameters<String>().categorical(categories))
    }

    @Test
    fun testContinuousDefault() {
        val expectedScale = NonPositionalContinuousScale<Double, Double>(
            null, null, null, null, null, null
        )
        assertEquals(expectedScale, Scale.continuous<Double, Double>())
        assertEquals(expectedScale, CommonNonPositionalMappingParametersContinuous<Double, Double>().continuous())
    }

    @Test
    fun testContinuous() {
        val domainLimits = 23.0..129.13
        val rangeLimits = 0.0F..1.0F
        val expectedScale = NonPositionalContinuousScale<Double, Float>(
            domainLimits.start, domainLimits.endInclusive,
            rangeLimits.start, rangeLimits.endInclusive, null, null
        )
        assertEquals(expectedScale, Scale.continuous(rangeLimits, domainLimits))
        assertEquals(
            expectedScale,
            CommonNonPositionalMappingParametersContinuous<Double, Float>().continuous(
                rangeLimits, domainLimits)
        )
    }

    @Test
    fun testCategoricalDefault() {
        val expectedScale = NonPositionalCategoricalScale<String, Color>(null, null)
        assertEquals(expectedScale, Scale.categorical<String, Color>())
        assertEquals(expectedScale, CommonNonPositionalMappingParameters<String, Color>().categorical())
    }

    @Test
    fun testCategorical() {
        val domainCategories = listOf(1, 3, 100)
        val rangeCategories = listOf(Color.RED, Color.BLACK, Color.BLUE)
        val expectedScale = NonPositionalCategoricalScale<Int, Color>(domainCategories, rangeCategories)
        assertEquals(
            expectedScale, Scale.categorical<Int, Color>(
                *domainCategories.zip(rangeCategories).toTypedArray()
            )
        )
        assertEquals(
            expectedScale,
            CommonNonPositionalMappingParameters<Int, Color>().categorical(rangeCategories, domainCategories)
        )

    }
}
