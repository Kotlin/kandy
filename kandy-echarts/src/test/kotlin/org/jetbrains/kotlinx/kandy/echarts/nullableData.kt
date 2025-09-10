package org.jetbrains.kotlinx.kandy.echarts

import org.jetbrains.kotlinx.dataframe.api.column
import org.jetbrains.kotlinx.kandy.dsl.continuous
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.echarts.io.toJson
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.x
import org.jetbrains.kotlinx.kandy.echarts.layers.aes.y
import org.jetbrains.kotlinx.kandy.echarts.layers.area
import org.jetbrains.kotlinx.kandy.echarts.layers.bars
import org.jetbrains.kotlinx.kandy.echarts.layers.line
import kotlin.test.Ignore
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

    private val days = column<String?>("days")
    private val nums = column<Int?>("nums")
    private val nums2 = column<Int?>("nums2")
    private val colors = column<String?>("colors")
    private val sizes = column<Double?>("sizes")

    @Test
    fun `line with nulls`() {
        val expected = """
        {
            "xAxis": {
                "type": "category"
            },
            "yAxis": {
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
                        1
                    ],
                    [
                        "second",
                        2
                    ],
                    [
                        null,
                        3
                    ],
                    [
                        "fourth",
                        null
                    ],
                    [
                        "fifth",
                        5
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
                        "y": [
                            "nums"
                        ]
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
                    "type": "category"
                },
                "yAxis": {
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
                            1
                        ],
                        [
                            "second",
                            2
                        ],
                        [
                            null,
                            3
                        ],
                        [
                            "fourth",
                            null
                        ],
                        [
                            "fifth",
                            5
                        ]
                    ]
                },
                "series": [
                    {
                        "type": "line",
                        "name": "days nums",
                        "showSymbol": false,
                        "areaStyle": {},
                        "encode": {
                            "x": "days",
                            "y": [
                                "nums"
                            ]
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
                    "type": "category"
                },
                "yAxis": {
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
                            1
                        ],
                        [
                            "second",
                            2
                        ],
                        [
                            null,
                            3
                        ],
                        [
                            "fourth",
                            4
                        ],
                        [
                            "fifth",
                            5
                        ]
                    ]
                },
                "series": [
                    {
                        "type": "bar",
                        "name": "days nums",
                        "encode": {
                            "x": "days",
                            "y": [
                                "nums"
                            ]
                        }
                    }
                ]
            }
        """.trimIndent()

        val actual = plot(data) {
            x(days)
            y(nums) {
                scale = continuous(nullValue = 4)
            }
            bars {}
        }

        assertEquals(expected, actual.toJson())
    }
}