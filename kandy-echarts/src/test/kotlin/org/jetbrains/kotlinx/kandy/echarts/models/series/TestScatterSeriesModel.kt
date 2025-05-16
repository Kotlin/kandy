package org.jetbrains.kotlinx.kandy.echarts.models.seires

import kotlinx.serialization.encodeToString
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.settings.px
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.Series
import kotlin.test.Test
import kotlin.test.assertEquals

class TestScatterSeriesModel {
    @Test
    fun `test example scatter object`() {
        val expected: String = """
            {
                "type": "scatter",
                "id": "scatterId",
                "name": "test scatter",
                "colorBy": "series",
                "coordinateSystem": "cartesian2d",
                "xAxisIndex": 0,
                "yAxisIndex": 0,
                "polarIndex": 0,
                "geoIndex": 0,
                "calendarIndex": 0,
                "legendHoverLink": true,
                "symbol": "circle",
                "symbolSize": 10,
                "symbolKeepAspect": false,
                "symbolOffset": [
                    0,
                    0
                ],
                "large": false,
                "largeThreshold": 2000,
                "cursor": "pointer",
                "progressive": 400,
                "progressiveThreshold": 3000,
                "seriesLayoutBy": "column",
                "datasetIndex": 0,
                "data": [
                    [
                        "Mon",
                        150
                    ],
                    [
                        "Tue",
                        230
                    ],
                    [
                        "Wed",
                        224
                    ],
                    [
                        "Thu",
                        218
                    ],
                    [
                        "Fri",
                        135
                    ],
                    [
                        "Sat",
                        147
                    ],
                    [
                        "Sun",
                        260
                    ]
                ],
                "clip": true,
                "zlevel": 0,
                "z": 2,
                "silent": false,
                "animation": true,
                "animationThreshold": 2000,
                "animationDuration": 1000,
                "animationEasing": "cubicOut",
                "animationDelay": 0,
                "animationDurationUpdate": 300,
                "animationEasingUpdate": "cubicOut",
                "animationDelayUpdate": 0
            }
        """.trimIndent()
        val actual: Series = ScatterSeries(
            id = "scatterId", name = "test scatter", colorBy = "series", coordinateSystem = "cartesian2d",
            xAxisIndex = 0, yAxisIndex = 0, polarIndex = 0, geoIndex = 0, calendarIndex = 0, legendHoverLink = true,
            symbol = "circle", symbolSize = listOf(10), symbolKeepAspect = false, symbolOffset = listOf(0.px, 0.px),
            large = false, largeThreshold = 2000, cursor = "pointer", progressive = 400, progressiveThreshold = 3000,
            seriesLayoutBy = "column", datasetIndex = 0,
            data = listOf(
                listOf(Element.StringEl("Mon"), Element.IntEl(150)),
                listOf(Element.StringEl("Tue"), Element.IntEl(230)),
                listOf(Element.StringEl("Wed"), Element.IntEl(224)),
                listOf(Element.StringEl("Thu"), Element.IntEl(218)),
                listOf(Element.StringEl("Fri"), Element.IntEl(135)),
                listOf(Element.StringEl("Sat"), Element.IntEl(147)),
                listOf(Element.StringEl("Sun"), Element.IntEl(260))
            ),
            clip = true, zlevel = 0, z = 2, silent = false, animation = true, animationThreshold = 2000,
            animationDuration = 1000, animationEasing = "cubicOut", animationDelay = 0, animationDurationUpdate = 300,
            animationEasingUpdate = "cubicOut", animationDelayUpdate = 0
        )
        assertEquals(expected, json.encodeToString(actual))
    }
}