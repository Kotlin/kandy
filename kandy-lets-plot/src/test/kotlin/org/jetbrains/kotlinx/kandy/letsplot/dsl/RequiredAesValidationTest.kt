package org.jetbrains.kotlinx.kandy.letsplot.dsl

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.Bins
import org.jetbrains.kotlinx.kandy.letsplot.stat.bin.statBin
import org.jetbrains.kotlinx.kandy.letsplot.y
import kotlin.test.Test
import kotlin.test.assertFailsWith

internal class RequiredAesValidationTest {

    @Test
    fun testPoints() {
        assertFailsWith<IllegalArgumentException>("\"x\" is not assigned.") {
            plot {
                points {
                    y(listOf(1, 2, 3))
                }
            }
        }
    }

    @Test
    fun testStatBin() {
        val valCol = column<Int>("val")
        assertFailsWith<IllegalArgumentException>("\"y\" is not assigned.") {
            plot(mapOf("val" to listOf(1, 2, 3, 4, 5))) {
                y(valCol)
                statBin(valCol, Bins.byNumber(5)) {
                    points {
                        x(listOf(1, 2, 3))
                    }
                }
            }
        }
    }

}
