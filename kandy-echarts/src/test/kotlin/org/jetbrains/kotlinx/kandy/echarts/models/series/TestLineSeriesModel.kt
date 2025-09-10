package org.jetbrains.kotlinx.kandy.echarts.models.seires

import kotlinx.serialization.*
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.settings.px
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.LineSeries
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.Series
import kotlin.test.Test
import kotlin.test.assertEquals

class TestLineSeriesModel {

    @Test
    fun `test example line object`() {
        val expected: String = """
            {
                "type": "line",
                "id": "class1",
                "name": "test line",
                "colorBy": "series",
                "coordinateSystem": "cartesian2d",
                "xAxisIndex": 0,
                "yAxisIndex": 0,
                "polarIndex": 0,
                "symbol": "emptyCircle",
                "symbolSize": 4,
                "symbolKeepAspect": false,
                "symbolOffset": [
                    0,
                    0
                ],
                "showSymbol": true,
                "showAllSymbol": "auto",
                "legendHoverLink": true,
                "stackStrategy": "samesign",
                "cursor": "pointer",
                "clip": true,
                "triggerLineEvent": false,
                "selectedMode": "false",
                "smooth": false,
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
        val actual: Series = LineSeries(
            id = "class1", name = "test line", colorBy = "series", coordinateSystem = "cartesian2d",
            xAxisIndex = 0, yAxisIndex = 0, polarIndex = 0,
            symbol = "emptyCircle", symbolSize = listOf(4), symbolKeepAspect = false,
            symbolOffset = listOf(0.px, 0.px), showSymbol = true,
            showAllSymbol = "auto", legendHoverLink = true, stackStrategy = "samesign", cursor = "pointer", clip = true,
            triggerLineEvent = false, selectedMode = "false", smooth = false, seriesLayoutBy = "column",
            datasetIndex = 0, data = listOf(
                listOf(Element.StringEl("Mon"), Element.IntEl(150)),
                listOf(Element.StringEl("Tue"), Element.IntEl(230)),
                listOf(Element.StringEl("Wed"), Element.IntEl(224)),
                listOf(Element.StringEl("Thu"), Element.IntEl(218)),
                listOf(Element.StringEl("Fri"), Element.IntEl(135)),
                listOf(Element.StringEl("Sat"), Element.IntEl(147)),
                listOf(Element.StringEl("Sun"), Element.IntEl(260))
            ),
            zlevel = 0, z = 2, silent = false, animation = true,
            animationThreshold = 2000, animationDuration = 1000, animationEasing = "linear", animationDelay = 0,
            animationDurationUpdate = 300, animationEasingUpdate = "cubicOut", animationDelayUpdate = 0
        )
        assertEquals(expected, json.encodeToString(actual))
    }
}