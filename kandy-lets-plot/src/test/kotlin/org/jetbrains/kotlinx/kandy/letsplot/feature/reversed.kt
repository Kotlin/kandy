package org.jetbrains.kotlinx.kandy.letsplot.feature

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.barsH
import kotlin.test.Test

class ReversedTest {

    // TODO error
    @Test
    fun `test reversed plot`() {
        val plot = plot {
            bars {
                x(listOf(1, 2, 3))
                y(listOf(1, 2, 3))
                reversed = true
            }
        }

        println(plot.features[Reversed.FEATURE_NAME])
    }

    // TODO error
    @Test
    fun `reversed by barH`() {
        val plot = plot {
            barsH {
                x(listOf(1, 2, 3))
                y(listOf(1, 2, 3))
            }
        }

        println(plot.features[Reversed.FEATURE_NAME])
    }
}