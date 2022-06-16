package org.jetbrains.kotlinx.ggdsl.letsplot.translator

import org.jetbrains.kotlinx.ggdsl.ir.aes.*
import org.jetbrains.kotlinx.ggdsl.ir.scale.*
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import jetbrains.letsPlot.intern.toSpec
import org.jetbrains.kotlinx.ggdsl.ir.geom.CommonGeom
import org.jetbrains.kotlinx.ggdsl.ir.geom.Geom
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class ScaleWrappingTest {
    @Test
    fun testPos() {
        val range = 2.0 to 11.1
        val scale = PositionalContinuousScale(range)
        val wrappedScale = scale.wrap(X, Geom.LINE)
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf<String, Any>(
                "aesthetic" to "x",
                "limits" to listOf(2.0, 11.1)
            ),
            wrappedScale.toSpec()
        )
    }
    @Test
    fun testNonPos() {
        val values = listOf(Color.BLACK, Color.RED, Color.GREEN)
        val scale = NonPositionalCategoricalScale<String, Color>(rangeValues = values)
        val wrappedScale = scale.wrap(COLOR, Geom.POINT)
        assertNotNull(wrappedScale)
        assertEquals(
            mapOf<String, Any>(
                "aesthetic" to "fill",
                "values" to listOf("black", "red", "green")
            ),
            wrappedScale.toSpec()
        )
    }

    @Test
    fun testDefaults() {
        val mockGeom = CommonGeom("mock")
        assertNull(UnspecifiedDefaultScale.wrap(SYMBOL, mockGeom))
        assertNull(PositionalCategoricalDefaultScale.wrap(Y, mockGeom))
        assertNull(PositionalContinuousDefaultScale.wrap(Y, mockGeom))
        assertNull(NonPositionalCategoricalDefaultScale.wrap(COLOR, mockGeom))
        assertNull(NonPositionalContinuousDefaultScale.wrap(SIZE, mockGeom))
    }
}