package org.jetbrains.kotlinx.kandy.letsplot.dsl

import org.jetbrains.kotlinx.kandy.dsl.categorical
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import kotlin.test.Test
import kotlin.test.assertFailsWith

class AlphaBoundsValidationTest {

    @Test
    fun testSetting() {
        assertFailsWith<IllegalArgumentException>("Value \"1.1\" of \"alpha\" is outside the range [0.0, 1.0].") {
            plot(dataset) {
                points {
                    x(time)
                    y(type)
                    alpha = 1.1
                }
            }
        }
    }

    @Test
    fun testScaleContinuous() {
        assertFailsWith<IllegalArgumentException>("Value \"-0.5\" of \"alpha\" is outside the range [0.0, 1.0].") {
            plot(dataset) {
                points {
                    x(time)
                    y(type)
                    alpha(time) {
                        scale = continuous(-0.5..1.0)
                    }
                }
            }
        }
    }

    @Test
    fun testScaleCategorical() {
        assertFailsWith<IllegalArgumentException>("Value \"3.0\" of \"alpha\" is outside the range [0.0, 1.0].") {
            plot(dataset) {
                points {
                    x(time)
                    y(type)
                    alpha(type) {
                        scale = categorical(listOf(0.3, 3.0))
                    }
                }
            }
        }
    }
}