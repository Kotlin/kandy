package org.jetbrains.kotlinx.kandy.echarts.dsl

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.echarts.features.label.LabelPosition
import org.jetbrains.kotlinx.kandy.echarts.features.label.label
import org.jetbrains.kotlinx.kandy.echarts.features.text.FontFamily
import org.jetbrains.kotlinx.kandy.echarts.features.text.FontStyle
import org.jetbrains.kotlinx.kandy.echarts.features.text.FontWeight
import org.jetbrains.kotlinx.kandy.echarts.io.toJson
import org.jetbrains.kotlinx.kandy.echarts.layers.area
import org.jetbrains.kotlinx.kandy.echarts.layers.bars
import org.jetbrains.kotlinx.kandy.echarts.layers.line
import org.jetbrains.kotlinx.kandy.echarts.layers.points
import org.jetbrains.kotlinx.kandy.echarts.settings.LineType
import org.jetbrains.kotlinx.kandy.echarts.settings.LinearGradient
import org.jetbrains.kotlinx.kandy.echarts.settings.pct
import org.jetbrains.kotlinx.kandy.util.color.Color
import org.jetbrains.kotlinx.kandy.util.context.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class LabelTests {

    @Test
    fun `labels on line`() {
        val actual = plot(mapOf()) {
            line {
                label {
                    position = LabelPosition.fromPct(10.pct to 10.pct, -90)
                    formatter = "{b}"
                    textStyle.color = Color.named("blue")
                    border {
                        color = Color.hex("#CCC")
                        width = .5
                    }
                }
            }
        }

        val expected = """
            {
                "series": [
                    {
                        "type": "line",
                        "showSymbol": false,
                        "label": {
                            "show": true,
                            "position": [
                                "10%",
                                "10%"
                            ],
                            "rotate": -90,
                            "formatter": "{b}",
                            "color": "blue",
                            "borderColor": "#CCC",
                            "borderWidth": 0.5
                        }
                    }
                ]
            }
        """.trimIndent()

        assertEquals(expected, actual.toJson())
    }

    @Test
    fun `labels on area`() {
        val actual = plot(mapOf()) {
            area {
                label {
                    position = LabelPosition.inside(10)
                    formatter = "format {a}, {b}, {c}, {d}"
                    textStyle.color = Color.named("blue")
                }
            }
        }

        val expected = """
            {
                "series": [
                    {
                        "type": "line",
                        "showSymbol": false,
                        "label": {
                            "show": true,
                            "position": "inside",
                            "distance": 10,
                            "formatter": "format {a}, {b}, {c}, {d}",
                            "color": "blue"
                        },
                        "areaStyle": {}
                    }
                ]
            }
        """.trimIndent()

        assertEquals(expected, actual.toJson())
    }

    @Test
    fun `labels on bars`() {
        val actual = plot(mapOf()) {
            bars {
                label {
                    border {
                        color = Color.hex("#CCC")
                        width = .5
                        type = LineType.DASHED
                        radius = 10.0
                    }
                }
            }
        }

        val expected = """
            {
                "series": [
                    {
                        "type": "bar",
                        "label": {
                            "show": true,
                            "borderColor": "#CCC",
                            "borderWidth": 0.5,
                            "borderType": "dashed",
                            "borderRadius": 10.0
                        }
                    }
                ]
            }
        """.trimIndent()

        assertEquals(expected, actual.toJson())
    }

    @Test
    fun `labels on points`() {
        val actual = plot(mapOf()) {
            points {
                label {
                    position = LabelPosition.BOTTOM
                    formatter = "name of {a}, \n{b}"
                    textStyle {
                        color = LinearGradient(Color.GREY to Color.RED)
                        fontStyle = FontStyle.ITALIC
                        fontWeight = FontWeight.BOLD
                        fontFamily = FontFamily.MONOSPACE
                    }
                }
            }
        }

        val expected = """
            {
                "series": [
                    {
                        "type": "scatter",
                        "label": {
                            "show": true,
                            "position": "bottom",
                            "formatter": "name of {a}, \n{b}",
                            "color": {
                                "type": "linear",
                                "colorStops": [
                                    {
                                        "offset": 0.0,
                                        "color": "#a39999"
                                    },
                                    {
                                        "offset": 1.0,
                                        "color": "#ee6666"
                                    }
                                ]
                            },
                            "fontStyle": "italic",
                            "fontWeight": "bold",
                            "fontFamily": "monospace"
                        }
                    }
                ]
            }
        """.trimIndent()

        assertEquals(expected, actual.toJson())
    }
}