package org.jetbrains.kotlinx.kandy.echarts.models.seires

import kotlinx.serialization.encodeToString
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.settings.pct
import org.jetbrains.kotlinx.kandy.echarts.settings.px
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.*
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.option.series.Series
import kotlin.test.Test
import kotlin.test.assertEquals

class TestPieSeriesModel {
    @Test
    fun `test example pie object`() {
        val expected: String = """
            {
                "type": "pie",
                "id": "pieId",
                "name": "test pie",
                "colorBy": "data",
                "legendHoverLink": true,
                "geoIndex": 0,
                "calendarIndex": 0,
                "selectedOffset": 10,
                "clockwise": true,
                "startAngle": 90,
                "minAngle": 0,
                "minShowLabelAngle": 0,
                "avoidLabelOverlap": true,
                "stillShowZeroSum": true,
                "percentPrecision": 2,
                "cursor": "pointer",
                "zlevel": 0,
                "z": 2,
                "left": 0,
                "top": 0,
                "right": 0,
                "bottom": 0,
                "showEmptyCircle": true,
                "center": [
                    "50%",
                    "50%"
                ],
                "radius": [
                    0,
                    "75%"
                ],
                "seriesLayoutBy": "column",
                "datasetIndex": 0,
                "silent": false,
                "animationType": "expansion",
                "animationTypeUpdate": "transition",
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
        val actual: Series = PieSeries(
            id = "pieId", name = "test pie", colorBy = "data", legendHoverLink = true, geoIndex = 0, calendarIndex = 0,
            selectedOffset = 10, clockwise = true, startAngle = 90, minAngle = 0, minShowLabelAngle = 0,
            avoidLabelOverlap = true, stillShowZeroSum = true, percentPrecision = 2, cursor = "pointer", zlevel = 0,
            z = 2, left = 0.px, top = 0.px, right = 0.px, bottom = 0.px, showEmptyCircle = true,
            center = listOf(50.pct, 50.pct), radius = listOf(0.px, 75.pct), seriesLayoutBy = "column", datasetIndex = 0,
            silent = false, animationType = "expansion", animationTypeUpdate = "transition", animation = true,
            animationThreshold = 2000, animationDuration = 1000, animationEasing = "cubicOut", animationDelay = 0,
            animationDurationUpdate = 300, animationEasingUpdate = "cubicOut", animationDelayUpdate = 0
        )
        assertEquals(expected, json.encodeToString(actual))
    }
}