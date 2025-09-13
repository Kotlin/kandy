package org.jetbrains.kotlinx.kandy.letsplot.scales

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.Axis

class AxisTest {

    @Test
    fun `test breaksLabeled valid pair`() {
        val axis = Axis<Int>()
        axis.breaksLabeled(1 to "One", 2 to "Two", 3 to "Three")

        assertEquals(listOf(1, 2, 3), axis.breaks)
        assertEquals(listOf("One", "Two", "Three"), axis.labels)
    }

    @Test
    fun `test breaksLabeled with valid lists`() {
        val axis = Axis<Double>()
        axis.breaksLabeled(listOf(1.0, 2.0, 3.0), listOf("Low", "Medium", "High"))

        assertEquals(listOf(1.0, 2.0, 3.0), axis.breaks)
        assertEquals(listOf("Low", "Medium", "High"), axis.labels)
    }

    @Test
    fun `test breaksLabeled with lists mismatched sizes`() {
        val axis = Axis<Double>()
        assertFailsWith<IllegalArgumentException> {
            axis.breaksLabeled(listOf(1.0, 2.0), listOf("Low"))
        }
    }
}
