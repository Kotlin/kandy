/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedList
import org.jetbrains.kotlinx.ggdsl.dsl.internal.typedPair
import org.jetbrains.kotlinx.ggdsl.ir.scale.NonPositionalCategoricalScale
import org.jetbrains.kotlinx.ggdsl.ir.scale.PositionalContinuousScale
import org.jetbrains.kotlinx.ggdsl.letsplot.FILL
import org.jetbrains.kotlinx.ggdsl.letsplot.X
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.letsPlot.intern.toSpec
import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class ScaleWrappingTest {
    @Test
    fun testPos() {
        val range = 2.0 to 11.1
        val scale = PositionalContinuousScale<Double>(range.typedPair())
        val wrappedScale = scale.wrap(X, typeOf<Double>())
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
        val scale = NonPositionalCategoricalScale<String, Color>(rangeValues = values.typedList())
        val wrappedScale = scale.wrap(FILL, typeOf<String>())
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf(
                "aesthetic" to "fill",
                "values" to listOf("#000000", "#ee6666", "#3ba272")
            ),
            wrappedScale.toSpec()
        )
    }
    /*
        @Test
        fun testDefaults() {
            assertNull(DefaultUnspecifiedScale.wrap(SHAPE, mapping.domainType))
            assertNull(PositionalCategoricalUnspecifiedScale.wrap(Y, mapping.domainType))
            assertNull(PositionalContinuousUnspecifiedScale().wrap(Y, mapping.domainType))
            assertNull(NonPositionalCategoricalUnspecifiedScale.wrap(COLOR, mapping.domainType))
            assertNull(NonPositionalContinuousUnspecifiedScale().wrap(SIZE, mapping.domainType))
        }

     */
}
