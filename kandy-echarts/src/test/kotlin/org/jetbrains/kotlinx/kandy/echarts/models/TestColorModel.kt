package org.jetbrains.kotlinx.kandy.echarts.models

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.kotlinx.kandy.echarts.models.series.json
import org.jetbrains.kotlinx.kandy.echarts.translator.option.ColorStop
import org.jetbrains.kotlinx.kandy.echarts.translator.option.EchartsColor
import kotlin.test.Test
import kotlin.test.assertEquals

class TestColorModel {
    @Test
    fun `test rgb color`() =
        assertEquals("\"rgb(128, 128, 128)\"", json.encodeToString(EchartsColor.Rgb(128, 128, 128)))


    @Test
    fun `test rgba color`() =
        assertEquals(
            "\"rgba(128, 128, 128, 0.5)\"",
            json.encodeToString(EchartsColor.Rgba(128, 128, 128, 0.5))
        )

    @Test
    fun `test hex color`() =
        assertEquals("\"#ccc\"", json.encodeToString(EchartsColor.Hex("#ccc")))

    @Test
    fun `test list of colors`() {
        val listOfRgba = listOf(
            EchartsColor.Rgba(r = 84, g = 112, b = 198, 0.3),
            EchartsColor.Rgba(r = 145, g = 204, b = 117, 0.5),
            EchartsColor.Rgba(r = 250, g = 200, b = 88, 0.7)
        )
        val listOfHex = listOfRgba.map { it.toHex() }
        val listOfRgb = listOfRgba.map { EchartsColor.Rgb(it.r, it.g, it.b) }

        val expectedHex = "[\"#5470c64c\",\"#91cc757f\",\"#fac858b2\"]"
        val expectedRgb = "[\"rgb(84, 112, 198)\",\"rgb(145, 204, 117)\",\"rgb(250, 200, 88)\"]"
        val expectedRgba = "[\"rgba(84, 112, 198, 0.3)\",\"rgba(145, 204, 117, 0.5)\",\"rgba(250, 200, 88, 0.7)\"]"

        assertEquals(expectedHex, Json.encodeToString(listOfHex))
        assertEquals(expectedRgb, Json.encodeToString(listOfRgb))
        assertEquals(expectedRgba, Json.encodeToString(listOfRgba))
    }


    @Test
    fun `test linear gradient color`() {
        val expected =
            "{\"type\":\"linear\",\"x\":0.0,\"y\":0.0,\"x2\":0.0,\"y2\":1.0,\"colorStops\":[{\"offset\":0.0,\"color\":\"#ccc\"},{\"offset\":1.0,\"color\":\"#fff\"}]}"
        val linearGradient: EchartsColor =
            EchartsColor.LinearGradient(
                0.0, 0.0, 0.0, 1.0,
                listOf(ColorStop(0.0, EchartsColor.Hex("#ccc")), ColorStop(1.0, EchartsColor.Hex("#fff")))
            )
        assertEquals(expected, Json.encodeToString(linearGradient))
    }

    @Test
    fun `test radian gradient color`() {
        val expected =
            "{\"type\":\"radial\",\"x\":0.5,\"y\":0.5,\"r\":0.5,\"colorStops\":[{\"offset\":0.0,\"color\":\"#ccc\"},{\"offset\":1.0,\"color\":\"#fff\"}]}"
        val radialGradient: EchartsColor =
            EchartsColor.RadialGradient(
                0.5, 0.5, 0.5,
                listOf(ColorStop(0.0, EchartsColor.Hex("#ccc")), ColorStop(1.0, EchartsColor.Hex("#fff")))
            )
        assertEquals(expected, Json.encodeToString(radialGradient))
    }
}