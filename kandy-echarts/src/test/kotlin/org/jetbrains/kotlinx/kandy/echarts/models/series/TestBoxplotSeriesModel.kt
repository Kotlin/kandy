package org.jetbrains.kotlinx.kandy.echarts.models.seires

import kotlinx.serialization.encodeToString
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.settings.px
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.Series
import kotlin.test.Test
import kotlin.test.assertEquals

class TestBoxplotSeriesModel {
    @Test
    fun `test example boxplot object`() {
        val expected: String = """
            {
                "type": "boxplot",
                "id": "boxplotId",
                "coordinateSystem": "cartesian2d",
                "xAxisIndex": 0,
                "yAxisIndex": 0,
                "name": "test boxplot",
                "colorBy": "series",
                "legendHoverLink": true,
                "hoverAnimation": true,
                "boxWidth": [
                    7,
                    50
                ],
                "data": [
                    [
                        655.0,
                        850.0,
                        940.0,
                        980.0,
                        1175.0
                    ],
                    [
                        672.5,
                        800.0,
                        845.0,
                        885.0,
                        1012.5
                    ],
                    [
                        780.0,
                        840.0,
                        855.0,
                        880.0,
                        940.0
                    ],
                    [
                        621.25,
                        767.5,
                        815.0,
                        865.0,
                        1011.25
                    ]
                ],
                "zlevel": 0,
                "z": 2,
                "silent": false,
                "animationDuration": 800,
                "animationEasing": "elasticOut",
                "animationDelay": 0
            }
        """.trimIndent()
        val actual: Series = BoxplotSeries(
            id = "boxplotId", coordinateSystem = "cartesian2d", xAxisIndex = 0, yAxisIndex = 0, name = "test boxplot",
            colorBy = "series", legendHoverLink = true, hoverAnimation = true, boxWidth = listOf(7.px, 50.px),
            data = listOf(
                listOf(
                    Element.DoubleEl(655.0), Element.DoubleEl(850.0),
                    Element.DoubleEl(940.0), Element.DoubleEl(980.0), Element.DoubleEl(1175.0)
                ),
                listOf(
                    Element.DoubleEl(672.5), Element.DoubleEl(800.0),
                    Element.DoubleEl(845.0), Element.DoubleEl(885.0), Element.DoubleEl(1012.5)
                ),
                listOf(
                    Element.DoubleEl(780.0), Element.DoubleEl(840.0),
                    Element.DoubleEl(855.0), Element.DoubleEl(880.0), Element.DoubleEl(940.0)
                ),
                listOf(
                    Element.DoubleEl(621.25), Element.DoubleEl(767.5),
                    Element.DoubleEl(815.0), Element.DoubleEl(865.0), Element.DoubleEl(1011.25)
                ),
            ),
            zlevel = 0, z = 2, silent = false, animationDuration = 800, animationEasing = "elasticOut",
            animationDelay = 0,
        )
        assertEquals(expected, json.encodeToString(actual))
    }
}