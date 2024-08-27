/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy

import io.mockk.mockk
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
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ScaleTest {

    @Test
    fun `test continuous function with non-nullable domain`() {
        val mapping = CommonPositionalMappingParametersContinuous<Int>()
        val limits = 1..10

        val scale = mapping.continuous(limits)
        val expectedScale = PositionalContinuousScale(limits.first, limits.last, null, null)

        assertEquals(expectedScale, scale)
    }

    @Test
    fun `test continuous function with transformation`() {
        val mapping = CommonPositionalMappingParametersContinuous<Int>()
        val limits = 1..10
        val transform: PositionalTransform = mockk()

        val scale = mapping.continuous(limits, transform)
        val expectedScale = PositionalContinuousScale(limits.first, limits.last, null, transform)

        assertEquals(expectedScale, scale)
    }

    @Test
    fun `test continuous function with nullValue`() {
        val mapping = CommonPositionalMappingParametersContinuous<Int?>()
        val limits = -5..15

        val scale = mapping.continuous(limits = limits, nullValue = 3)
        val expectedScale = PositionalContinuousScale<Int?>(limits.first, limits.last, 3, null)

        assertEquals(expectedScale, scale)
    }

    @Test
    fun `test empty continuousPos`() {
        val expectedScale = PositionalContinuousScale<Float>(null, null, null, null)
        assertEquals(expectedScale, Scale.continuousPos())
        assertEquals(expectedScale, CommonPositionalMappingParametersContinuous<Float>().continuous())
    }

    @Test
    fun `test continuousPos function with non-nullable domain`() {
        val limits = -5..10

        val scale = Scale.continuousPos(limits)

        val expectedScale = PositionalContinuousScale(limits.first, limits.last, null, null)

        assertEquals(expectedScale, scale)
    }

    @Test
    fun `test continuousPos function with transformation`() {
        val limits = -5..7
        val transform: PositionalTransform = mockk()

        val scale = Scale.continuousPos(limits, transform)
        val expectedScale = PositionalContinuousScale(limits.first, limits.last, null, transform)
        println(scale)

        assertEquals(expectedScale, scale)
    }

    @Test
    fun `test continuousPos function with reversed limits`() {
        val limits: IntProgression = 10 downTo 1

        val scale = Scale.continuousPos(limits)

        val expectedScale = PositionalContinuousScale(limits.first, limits.last, null, null)
        scale.max
        expectedScale.max
        // TODO(https://github.com/Kotlin/kandy/issues/205)
        // assertEquals(expectedScale, scale)
    }

    @Test
    fun `test continuous function with non-null range and null transform and nullValue`() {
        val range = 0..10

        val firstScale = CommonNonPositionalMappingParametersContinuous<Float, Int>().continuous<Int, Float>(range)
        val secondScale = Scale.continuous<Int, Float>(range)
        val expectedScale =
            NonPositionalContinuousScale<Float, Int>(null, null, range.start, range.endInclusive, null, null)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test continuous function with non-null range and transform`() {
        val range = -5..7
        val transform: NonPositionalTransform = mockk()

        val firstScale =
            CommonNonPositionalMappingParametersContinuous<Int, Int>().continuous<Int, Int>(
                range,
                transform = transform
            )
        val secondScale = Scale.continuous<Int, Int>(range, transform = transform)
        val expectedScale = NonPositionalContinuousScale<Int, Int>(null, null, range.first, range.last, null, transform)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test continuous function with non-null range and nullValue`() {
        val range = -5.3..9.99
        val nullValue = -1.3

        val firstScale =
            CommonNonPositionalMappingParametersContinuous<Int, Double>().continuous<Double, Int>(
                range,
                nullValue = nullValue
            )
        val secondScale = Scale.continuous<Double, Int>(range, nullValue)
        val expectedScale =
            NonPositionalContinuousScale<Int, Double>(null, null, range.start, range.endInclusive, nullValue, null)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test continuous with complex domain and range limits`() {
        val domain = 23.0..129.13
        val range = 0.0F..1.0F

        val firstScale =
            CommonNonPositionalMappingParametersContinuous<Double, Float>().continuous(range, domain)
        val secondScale = Scale.continuous(range, domain)
        val expectedScale = NonPositionalContinuousScale(
            domain.start, domain.endInclusive, range.start, range.endInclusive, null, null
        )

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }


    @Test
    fun `test categorical function with null categories`() {
        val mapping = CommonPositionalMappingParameters<Int>()

        val scale = mapping.categorical<Int>()

        assertNull(scale.categories)
    }

    @Test
    fun `test categorical function with empty categories`() {
        val categories = emptyList<String>()
        val mapping = CommonPositionalMappingParameters<String>()

        val scale = mapping.categorical(categories)

        assertTrue(scale.categories?.isEmpty() ?: true)
    }

    @Test
    fun `test categorical function with non-empty categories`() {
        val categories = listOf("A", "B", "C")
        val mapping = CommonPositionalMappingParameters<String>()

        val scale = mapping.categorical(categories)

        assertEquals(categories, scale.categories)
    }

    @Test
    fun `test categorical function with non-empty vararg categories`() {
        val mapping = CommonPositionalMappingParameters<String>()

        val scale = mapping.categorical("A", "B", "C")

        assertEquals(listOf("A", "B", "C"), scale.categories)
    }

    @Test
    fun `test categoricalPos function with null categories`() {
        val scale = Scale.categoricalPos<String>()
        assertNull(scale.categories)
    }

    @Test
    fun `test categoricalPos function with empty categories`() {
        val categories = emptyList<String>()

        val scale = Scale.categoricalPos(categories)

        assertTrue(scale.categories?.isEmpty()!!)
    }

    @Test
    fun `test categoricalPos function with non-empty categories`() {
        val categories = listOf("A", "B", "C")

        val scale = Scale.categoricalPos(categories)

        assertEquals(categories, scale.categories)
    }

    @Test
    fun `test categoricalPos function with non-empty vararg categories`() {
        val scale = Scale.categoricalPos("A", "B", "C")

        assertEquals(listOf("A", "B", "C"), scale.categories)
    }

    @Test
    fun `test categorical function with non-null range and non-null domain`() {
        val range = listOf(1, 2, 3)
        val domain = listOf("A", "B", "C")

        val firstScale = CommonNonPositionalMappingParameters<String, Int>().categorical(range, domain)
        val secondScale = Scale.categorical<Int, String>(range, domain)
        val expectedScale = NonPositionalCategoricalScale(domain, range)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test categorical function with null range and non-null domain`() {
        val domain = listOf("A", "B", "C")

        val firstScale = CommonNonPositionalMappingParameters<String, Int>().categorical<Int, String>(domain = domain)
        val secondScale = Scale.categorical<Int, String>(domain = domain)
        val expectedScale = NonPositionalCategoricalScale<String, Int>(domain, null)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test categorical function with non-null range and null domain`() {
        val range = listOf(1, 2, 3)

        val firstScale = CommonNonPositionalMappingParameters<String, Int>().categorical<Int, String>(range = range)
        val secondScale = Scale.categorical<Int, String>(range = range)
        val expectedScale = NonPositionalCategoricalScale<String, Int>(null, range)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test categorical function with null range and null domain`() {
        val firstScale = CommonNonPositionalMappingParameters<String, String>().categorical<String, String>()
        val secondScale = Scale.categorical<String, String>()

        assertNull(firstScale.rangeValues)
        assertNull(firstScale.domainCategories)
        assertNull(secondScale.rangeValues)
        assertNull(secondScale.domainCategories)
    }

    @Test
    fun `test categorical function with multiple category-value pairs`() {
        val range = listOf(1, 2, 3)
        val domain = listOf("A", "B", "C")
        val categoriesToValues = domain.zip(range).toTypedArray()

        val firstScale = CommonNonPositionalMappingParameters<String, Int>().categorical(*categoriesToValues)
        val secondScale = Scale.categorical(*categoriesToValues)
        val expectedScale = NonPositionalCategoricalScale(listOf("A", "B", "C"), listOf(1, 2, 3))

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test categorical function with single category-value pair`() {
        val categoriesToValues = arrayOf("A" to 1)

        val firstScale = CommonNonPositionalMappingParameters<String, Int>().categorical(*categoriesToValues)
        val secondScale = Scale.categorical(*categoriesToValues)
        val expectedScale = NonPositionalCategoricalScale(listOf("A"), listOf(1))

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }

    @Test
    fun `test categorical function with no category-value pairs`() {
        val categoriesToValues = emptyArray<Pair<String, Int>>()

        val firstScale = CommonNonPositionalMappingParameters<String, Int>().categorical(*categoriesToValues)
        val secondScale = Scale.categorical(*categoriesToValues)

        assertTrue(firstScale.domainCategories!!.isEmpty())
        assertTrue(firstScale.rangeValues!!.isEmpty())
        assertTrue(secondScale.domainCategories!!.isEmpty())
        assertTrue(secondScale.rangeValues!!.isEmpty())
    }

    @Test
    fun `test categorical with domain range of colors`() {
        val domain = listOf(1, 3, 100)
        val range = listOf(Color.RED, Color.BLACK, Color.BLUE)
        val categoriesToValues = domain.zip(range).toTypedArray()


        val firstScale = CommonNonPositionalMappingParameters<Int, Color>().categorical<Int, Color>(*categoriesToValues)
        val secondScale = Scale.categorical<Int, Color>(*categoriesToValues)

        val expectedScale = NonPositionalCategoricalScale<Int, Color>(domain, range)

        assertEquals(expectedScale, firstScale)
        assertEquals(expectedScale, secondScale)
    }
}