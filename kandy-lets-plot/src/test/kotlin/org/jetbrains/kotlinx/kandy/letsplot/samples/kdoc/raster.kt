package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class RasterTest : KandyLetsPlotJupyterTest() {

    private val plotRaster = """
        // Assume we have data on average monthly temperatures in various cities.
        val dataset = dataFrameOf(
            "month" to List(4) { listOf("January", "February", "March", "April", "May") }.flatten(),
            "city" to List(5) { listOf("London", "Berlin", "Saint-Petersburg", "Yerevan") }.flatten(),
            "temp" to listOf(-5, -3, 0, 5, 10, -7, -5, -1, 4, 9, -10, -8, -5, 2, 7, -8, -6, -3, 3, 8)
        )

        // Now, let's create a heatmap based on this data.
        dataset.plot {
            raster {
                // Map temperatures to the x and y axes
                x("month")
                y("city")

                // Set the colors based on temperature
                fillColor("temp") {
                    // Here, you can set scale parameters so the colors correspond to temperatures
                }

                // Transparency and border parameters for each rectangle
                alpha = 0.8
                borderLine {
                    color = Color.BLACK
                    width = 0.5
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of raster in jupyter`() = plotRaster.checkCompilation()

    @Test
    fun `raster output in jupyter`() = assertOutput(execRendered(plotRaster))
}