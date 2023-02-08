package org.jetbrains.kotlinx.ggdsl.echarts

import org.jetbrains.kotlinx.ggdsl.dsl.*
import org.jetbrains.kotlinx.ggdsl.dsl.column.columnPointer
import org.jetbrains.kotlinx.ggdsl.echarts.aes.x
import org.jetbrains.kotlinx.ggdsl.echarts.aes.y
import org.jetbrains.kotlinx.ggdsl.echarts.layers.area
import org.jetbrains.kotlinx.ggdsl.echarts.layers.bars
import org.jetbrains.kotlinx.ggdsl.echarts.layers.line
import org.jetbrains.kotlinx.ggdsl.echarts.layers.points
import kotlin.test.Test
import kotlin.test.assertEquals

class NullableDataTest {

    private val data = mapOf(
        "days" to listOf("first", "second", null, "fourth", "fifth"),
        "nums" to listOf(1, 2, 3, null, 5),
        "nums2" to listOf(10, 20, null, 40, 50),
        "sizes" to listOf(0.2, null, 0.6, 0.8, 1.0),
        "colors" to listOf("red", "blue", "yellow", null, "purple")
    )

    private val days = columnPointer<String?>("days")
    private val nums = columnPointer<Int?>("nums")
    private val nums2 = columnPointer<Int?>("nums2")
    private val colors = columnPointer<String?>("colors")
    private val sizes = columnPointer<Double?>("sizes")

    @Test
    fun `line with nulls`() {
        val expected = """
        {
            "xAxis": {
                "show": true,
                "type": "category"
            },
            "yAxis": {
                "show": true,
                "type": "value"
            },
            "dataset": {
                "source": [
                    [
                        "days",
                        "nums"
                    ],
                    [
                        "first",
                        "1"
                    ],
                    [
                        "second",
                        "2"
                    ],
                    [
                        null,
                        "3"
                    ],
                    [
                        "fourth",
                        null
                    ],
                    [
                        "fifth",
                        "5"
                    ]
                ]
            },
            "series": [
                {
                    "type": "line",
                    "name": "days nums",
                    "showSymbol": false,
                    "encode": {
                        "x": "days",
                        "y": "nums"
                    }
                }
            ]
        }
        """.trimIndent()

        val actual = plot(data) {
            x(days)
            y(nums)
            line {}
        }.toJson()

        assertEquals(expected, actual)
    }

    @Test
    fun `area with nulls`() {
        val expected = """
            {
                "xAxis": {
                    "show": true,
                    "type": "category"
                },
                "yAxis": {
                    "show": true,
                    "type": "value"
                },
                "dataset": {
                    "source": [
                        [
                            "days",
                            "nums"
                        ],
                        [
                            "first",
                            "1"
                        ],
                        [
                            "second",
                            "2"
                        ],
                        [
                            null,
                            "3"
                        ],
                        [
                            "fourth",
                            null
                        ],
                        [
                            "fifth",
                            "5"
                        ]
                    ]
                },
                "series": [
                    {
                        "type": "line",
                        "name": "days nums",
                        "showSymbol": false,
                        "areaStyle": {
                        },
                        "encode": {
                            "x": "days",
                            "y": "nums"
                        }
                    }
                ]
            }
        """.trimIndent()

        val actual = plot(data) {
            x(days)
            area {
                y(nums)
            }
        }.toJson()


        assertEquals(expected, actual)
    }

    @Test
    fun `bars with nulls and fill null for y-axis`() {
        val expected = """
            {
                "xAxis": {
                    "show": true,
                    "type": "category"
                },
                "yAxis": {
                    "show": true,
                    "type": "value"
                },
                "dataset": {
                    "source": [
                        [
                            "days",
                            "nums"
                        ],
                        [
                            "first",
                            "1"
                        ],
                        [
                            "second",
                            "2"
                        ],
                        [
                            null,
                            "3"
                        ],
                        [
                            "fourth",
                            "4"
                        ],
                        [
                            "fifth",
                            "5"
                        ]
                    ]
                },
                "series": [
                    {
                        "type": "bar",
                        "name": "days nums",
                        "encode": {
                            "x": "days",
                            "y": "nums"
                        }
                    }
                ]
            }
        """.trimIndent()

        val actual = plot(data) {
            x(days)
            y(nums.scaled(continuousPos(nullValue = 4)))
            bars {}
        }.toJson()


        assertEquals(expected, actual)
    }

    @Test
    fun `points with nulls`() {
        val option = plot(data) {
            x(nums2)
            y(nums)
            points {
                size(sizes.scaled())
            }
        }.toJson()

        println(option)
    }
}