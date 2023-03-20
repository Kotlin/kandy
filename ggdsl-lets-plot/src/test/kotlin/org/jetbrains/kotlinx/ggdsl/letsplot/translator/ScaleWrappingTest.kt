/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.COLOR
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.FILL
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.SIZE
import org.jetbrains.kotlinx.ggdsl.letsplot.internal.X
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class ScaleWrappingTest {
    @Test
    fun testPos() {
        val range = 2.0 .. 11.1
        val scale = PositionalContinuousScale<Double>(range.start, range.endInclusive, null, null)
        val wrappedScale = scale.wrap(X, typeOf<Double>(), null, false)
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf(
                "aesthetic" to "x",
                "limits" to listOf(2.0, 11.1)
            ),
            wrappedScale.toSpec()
        )
    }

    @Test
    fun testNonPos() {
        val values = listOf(Color.BLACK, Color.RED, Color.GREEN)
        val scale = NonPositionalCategoricalScale<String, Color>(
            null,
            rangeValues = values,
            //null,
        )
        val wrappedScale = scale.wrap(FILL, typeOf<String>(), null, false)
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf(
                "aesthetic" to "fill",
                "values" to listOf("#000000", "#ee6666", "#3ba272")
            ),
            wrappedScale.toSpec()
        )
    }

    @Test
    fun testNonPosCategoricalNull() {
        val categories = listOf(1, 3, 5)
        val values = listOf(45.1, 728.1, 0.0001)
        val nullValue = 1.123
        val scale = NonPositionalCategoricalScale<Int?, Double>(
            (categories + null),
            (values + nullValue),
            //null,
        )
        val wrappedScale = scale.wrap(SIZE, typeOf<Double>(), null, false)
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf(
                "aesthetic" to "size",
                "limits" to categories.map { it.toDouble() },
                "values" to values + nullValue, // TODO!!
                "na_value" to nullValue,
            ),
            wrappedScale.toSpec()
        )
    }

    @Test
    fun testNonPosContinuousNull() {
        val domainLimits = 0.5 .. 0.9
        val rangeLimits: ClosedRange<Color> = Color.RED .. Color.GREEN
        val nullValue = Color.GREY
        val scale = NonPositionalContinuousScale<Double, Color>(
            domainLimits.start,
            domainLimits.endInclusive,
            rangeLimits.start,
            rangeLimits.endInclusive,
            nullValue,
            null
        )
        val wrappedScale = scale.wrap(COLOR, typeOf<Double>(), null, false)
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf(
                "aesthetic" to "color",
                "scale_mapper_kind" to "color_gradient",
                "limits" to listOf(domainLimits.start, domainLimits.endInclusive),
                "low" to "#ee6666",
                "high" to "#3ba272",
                "na_value" to "#a39999",
            ),
            wrappedScale.toSpec()
        )
    }
}
