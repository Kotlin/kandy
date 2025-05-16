package org.jetbrains.kotlinx.kandy.echarts.models.seires

import kotlinx.serialization.encodeToString
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.settings.pct
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.BarSeries
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.Series
import kotlin.test.Test
import kotlin.test.assertEquals

class TestBarSeriesModel {
    @Test
    fun `test example bar object`() {
        val expected: String = """
            {
                "type": "bar",
                "id": "barId",
                "name": "test bar",
                "colorBy": "series",
                "legendHoverLink": true,
                "coordinateSystem": "cartesian2d",
                "xAxisIndex": 0,
                "yAxisIndex": 0,
                "polarIndex": 0,
                "roundCap": false,
                "realtimeSort": false,
                "showBackground": false,
                "stackStrategy": "samesign",
                "cursor": "pointer",
                "barMinHeight": 0,
                "barMinAngle": 0,
                "barGap": "30%",
                "barCategoryGap": "20%",
                "large": false,
                "largeThreshold": 400,
                "progressive": 5000,
                "progressiveThreshold": 3000,
                "progressiveChunkMode": "mod",
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
                "animationEasing": "linear",
                "animationDelay": 0,
                "animationDurationUpdate": 300,
                "animationEasingUpdate": "cubicOut",
                "animationDelayUpdate": 0
            }
        """.trimIndent()
        val actual: Series = BarSeries(
            id = "barId", name = "test bar", colorBy = "series", legendHoverLink = true,
            coordinateSystem = "cartesian2d", xAxisIndex = 0, yAxisIndex = 0, polarIndex = 0, roundCap = false,
            realtimeSort = false, showBackground = false, stackStrategy = "samesign", cursor = "pointer",
            barMinHeight = 0, barMinAngle = 0, barGap = 30.pct, barCategoryGap = 20.pct, large = false,
            largeThreshold = 400, progressive = 5000, progressiveThreshold = 3000, progressiveChunkMode = "mod",
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
            animationDuration = 1000, animationEasing = "linear", animationDelay = 0, animationDurationUpdate = 300,
            animationEasingUpdate = "cubicOut", animationDelayUpdate = 0
        )
        assertEquals(expected, json.encodeToString(actual))
    }
}