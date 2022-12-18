package org.jetbrains.kotlinx.ggdsl.echarts.dsl

import org.jetbrains.kotlinx.ggdsl.dsl.plot
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationEasing
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.AnimationType
import org.jetbrains.kotlinx.ggdsl.echarts.features.animation.animation
import org.jetbrains.kotlinx.ggdsl.echarts.layers.*
import org.jetbrains.kotlinx.ggdsl.echarts.toJson
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import kotlin.test.Test
import kotlin.test.assertEquals

/*
 * Tests if the received animation json matches the expected one
 */
private fun assertAnimationEq(
    actual: Plot,
    type: String? = null,
    animation: Boolean? = null, animationType: AnimationType? = null, animationThreshold: Int? = null,
    animationDuration: Int, animationEasing: AnimationEasing? = null, animationDelay: Int,
    lazyMessage: () -> Any
) {
    val expected = when (type) {
        null -> """
            {
                "animation": $animation,
                "animationThreshold": $animationThreshold,
                "animationDuration": $animationDuration,
                "animationEasing": "${animationEasing?.name}",
                "animationDelay": $animationDelay
            }
        """.trimIndent()

        "line" -> """
            {
                "series": [
                    {
                        "type": "line",
                        "showSymbol": false,
                        "animation": $animation,
                        "animationThreshold": $animationThreshold,
                        "animationDuration": $animationDuration,
                        "animationEasing": "${animationEasing?.name}",
                        "animationDelay": $animationDelay
                    }
                ]
            }
        """.trimIndent()

        "area" -> """
            {
                "series": [
                    {
                        "type": "line",
                        "showSymbol": false,
                        "areaStyle": {
                        },
                        "animation": $animation,
                        "animationThreshold": $animationThreshold,
                        "animationDuration": $animationDuration,
                        "animationEasing": "${animationEasing?.name}",
                        "animationDelay": $animationDelay
                    }
                ]
            }
        """.trimIndent()

        "bar", "scatter" -> """
            {
                "series": [
                    {
                        "type": "$type",
                        "animationThreshold": $animationThreshold,
                        "animationDuration": $animationDuration,
                        "animationDelay": $animationDelay
                    }
                ]
            }
        """.trimIndent()

        "pie" -> """
            {
                "series": [
                    {
                        "type": "pie",
                        "animationType": "${animationType?.type}",
                        "animation": $animation,
                        "animationThreshold": $animationThreshold,
                        "animationDuration": $animationDuration,
                        "animationEasing": "${animationEasing?.name}",
                        "animationDelay": $animationDelay
                    }
                ]
            }
        """.trimIndent()

        "boxplot", "candlestick" -> """
            {
                "series": [
                    {
                        "type": "$type",
                        "animationDuration": $animationDuration,
                        "animationEasing": "${animationEasing?.name}",
                        "animationDelay": $animationDelay
                    }
                ]
            }
        """.trimIndent()

        else -> throw AssertionError("Series types do not match those presented in the library: $type")
    }

    assertEquals(expected, actual.toJson(), lazyMessage().toString())
}

class AnimationTests {
    private var animation: Boolean = true
    private var animationType: AnimationType = AnimationType.SCALE
    private var animationThreshold: Int = 2000
    private var animationDuration: Int = 1000
    private var animationEasing: AnimationEasing = AnimationEasing.CUBIC_OUT
    private var animationDelay: Int = 0

    @Test
    fun `empty animation`() {
        val actual = plot {
            animation {  }
        }
        val expected = """
            {
            }
        """.trimIndent()
        assertEquals(expected, actual.toJson())
    }

    @Test
    fun `false animation`() {
        val actual = plot {
            animation { enable = false }
        }
        val expected = """
            {
                "animation": false
            }
        """.trimIndent()
        assertEquals(expected, actual.toJson())
    }

    @Test
    fun `animation of plot`() {
        val actual = plot {
            animation {
                enable = animation // true
                threshold = animationThreshold // 2000
                duration = animationDuration // 1000
                easing = animationEasing // cubicOut
                delay = animationDelay // 0
            }
        }

        assertAnimationEq(
            actual, animation = animation, animationThreshold = animationThreshold,
            animationDuration = animationDuration, animationEasing = animationEasing, animationDelay = animationDelay
        ) { "Plot animation not as expected" }
    }

    @Test
    fun `animation of line`() {
        animation = true
        animationThreshold = 1000
        animationDuration = 1500
        animationEasing = AnimationEasing.ELASTIC_OUT
        animationDelay = 100
        val actual = plot(mapOf()) {
            line {
                animation {
                    enable = animation
                    threshold = animationThreshold
                    duration = animationDuration
                    easing = animationEasing
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "line", animation = animation, animationThreshold = animationThreshold,
            animationDuration = animationDuration, animationEasing = animationEasing, animationDelay = animationDelay
        ) { "Line plot animation not as expected" }
    }

    @Test
    fun `animation of area`() {
        animation = true
        animationThreshold = 0
        animationDuration = 1
        animationEasing = AnimationEasing.ELASTIC_OUT
        animationDelay = -100
        val actual = plot(mapOf()) {
            area {
                animation {
                    enable = animation
                    threshold = animationThreshold
                    duration = animationDuration
                    easing = animationEasing
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "area", animation = animation, animationThreshold = animationThreshold,
            animationDuration = animationDuration, animationEasing = animationEasing, animationDelay = animationDelay
        ) { "Area plot animation not as expected" }
    }

    @Test
    fun `animation of bars`() {
        animationThreshold = 123
        animationDuration = 721
        animationDelay = 789
        val actual = plot(mapOf()) {
            bars {
                animation {
                    threshold = animationThreshold
                    duration = animationDuration
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "bar", animationThreshold = animationThreshold,
            animationDuration = animationDuration, animationDelay = animationDelay
        )
        { "Bar plot animation not as expected" }
    }

    @Test
    fun `animation of points`() {
        animationThreshold = 8923
        animationDuration = 841
        animationDelay = 12
        val actual = plot(mapOf()) {
            points {
                animation {
                    threshold = animationThreshold
                    duration = animationDuration
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "scatter", animationThreshold = animationThreshold,
            animationDuration = animationDuration, animationDelay = animationDelay
        )
        { "Scatter plot animation not as expected" }
    }

    @Test
    fun `animation of pie`() {
        animation = true
        animationType = AnimationType.SCALE
        animationThreshold = 875
        animationDuration = 952
        animationEasing = AnimationEasing.CUBIC_OUT
        animationDelay = 56
        val actual = plot(mapOf()) {
            pie {
                animation {
                    enable = animation
                    type = animationType
                    threshold = animationThreshold
                    duration = animationDuration
                    easing = animationEasing
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "pie",
            animation, animationType, animationThreshold, animationDuration, animationEasing, animationDelay
        )
        { "Pie plot animation not as expected" }
    }

    @Test
    fun `animation of boxplot`() {
        animationEasing = AnimationEasing.ELASTIC_OUT
        animationDuration = 84
        animationDelay = 0
        val actual = plot(mapOf()) {
            boxplot {
                animation {
                    easing = animationEasing
                    duration = animationDuration
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "boxplot",
            animationDuration = animationDuration, animationEasing = animationEasing, animationDelay = animationDelay
        )
        { "Boxplot animation not as expected" }
    }

    @Test
    fun `animation of candlestick`() {
        animationEasing = AnimationEasing.CUBIC_OUT
        animationDuration = 912
        animationDelay = 985
        val actual = plot(mapOf()) {
            candlestick {
                animation {
                    easing = animationEasing
                    duration = animationDuration
                    delay = animationDelay
                }
            }
        }

        assertAnimationEq(
            actual, type = "candlestick",
            animationDuration = animationDuration, animationEasing = animationEasing, animationDelay = animationDelay
        )
        { "Candlestick plot animation not as expected" }
    }
}