package org.jetbrains.kotlinx.kandy.letsplot.dsl

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
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
}
