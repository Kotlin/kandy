package org.jetbrains.kotlinx.kandy.letsplot.scales

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.jetbrains.kotlinx.kandy.letsplot.scales.guide.model.Legend

class LegendTest {

    @Test
    fun `test breaksLabeled with valid pair`() {
        val legend = Legend<Char, Any?>()
        legend.breaksLabeled('A' to "Label A", 'B' to "Label B")

        assertEquals(listOf('A', 'B'), legend.breaks)
        assertEquals(listOf("Label A", "Label B"), legend.labels)
    }

    @Test
    fun `test breaksLabeled with valid lists`() {
        val legend = Legend<Char, Any?>()
        legend.breaksLabeled(listOf('X', 'Y', 'Z'), listOf("X-label", "Y-label", "Z-label"))

        assertEquals(listOf('X', 'Y', 'Z'), legend.breaks)
        assertEquals(listOf("X-label", "Y-label", "Z-label"), legend.labels)
    }

    @Test
    fun `test breaksLabeled with lists mismatched sizes`() {
        val legend = Legend<Char, Any?>()
        assertFailsWith<IllegalArgumentException> {
            legend.breaksLabeled(listOf('A', 'B'), listOf("Only Label A"))
        }
    }
}
