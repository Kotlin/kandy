package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import kotlin.test.Test
import kotlin.test.assertEquals

class ReversedTest {

    @Test
    fun `test reversed plot`() {
        val plot = plot {
            bars {
                x(listOf(1, 2, 3))
                y(listOf(1, 2, 3))
                reversed = true
            }
        }

        assertEquals(Reversed(true), plot.layers.first().features[Reversed.FEATURE_NAME])
    }

    @Test
    fun `reversed by barH`() {
        val plot = plot {
            barsH {
                x(listOf(1, 2, 3))
                y(listOf(1, 2, 3))
            }
        }

        assertEquals(Reversed(true), plot.layers.first().features[Reversed.FEATURE_NAME])
    }
}