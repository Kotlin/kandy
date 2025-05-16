package org.jetbrains.kotlinx.kandy.echarts.models.seires

import kotlinx.serialization.encodeToString
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.Series
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCandlestickSeriesModel {
    @Test
    fun `test example candlestick object`() {
        val expected: String = """
            {
                "type": "candlestick",
                "id": "candlestickId",
                "coordinateSystem": "cartesian2d",
                "xAxisIndex": 0,
                "yAxisIndex": 0,
                "name": "test candlestick",
                "colorBy": "series",
                "legendHoverLink": true,
                "hoverAnimation": true,
                "large": true,
                "largeThreshold": 600,
                "progressive": 3000,
                "progressiveThreshold": 10000,
                "progressiveChunkMode": "mod",
                "data": [
                    [
                        2320.26,
                        2320.26,
                        2287.3,
                        2362.94
                    ],
                    [
                        2300.0,
                        2291.3,
                        2288.26,
                        2308.38
                    ]
                ],
                "clip": true,
                "zlevel": 0,
                "z": 2,
                "silent": false,
                "animationDuration": 300,
                "animationEasing": "linear",
                "animationDelay": 0
            }
        """.trimIndent()
        val actual: Series = CandlestickSeries(
            id = "candlestickId", coordinateSystem = "cartesian2d", xAxisIndex = 0, yAxisIndex = 0,
            name = "test candlestick", colorBy = "series", legendHoverLink = true, hoverAnimation = true, large = true,
            largeThreshold = 600, progressive = 3000, progressiveThreshold = 10000, progressiveChunkMode = "mod",
            data = listOf(
                listOf(
                    Element.DoubleEl(2320.26), Element.DoubleEl(2320.26),
                    Element.DoubleEl(2287.3), Element.DoubleEl(2362.94)
                ),
                listOf(
                    Element.DoubleEl(2300.0), Element.DoubleEl(2291.3),
                    Element.DoubleEl(2288.26), Element.DoubleEl(2308.38)
                ),
            ),
            clip = true, zlevel = 0, z = 2, silent = false,
            animationDuration = 300, animationEasing = "linear", animationDelay = 0
        )
        assertEquals(expected, json.encodeToString(actual))
    }
}