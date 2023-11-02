package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class SegmentsTest : KandyLetsPlotJupyterTest() {

    private val plotSegments = """
        val xS by columnOf(1, 2, 3, 4, 5)
        val xE by columnOf(4, 5, 6, 7, 8)
        val yS by columnOf(10, 20, 30, 40, 50)
        val yE by columnOf(50, 40, 30, 20, 10)
        val city by columnOf("CityA", "CityB", "CityC", "CityD", "CityE")

        plot {
            segments {
                // Positional mapping
                xBegin(xS)
                xEnd(xE)
                yBegin(yS)
                yEnd(yE)

                // Non-positional mapping
                color(city)

                // Non-positional settings
                width = 2.5
                alpha = 0.8
                lineType = LineType.DASHED
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of segments in jupyter`() = plotSegments.checkCompilation()

    @Test
    fun `segments output in jupyter`() = assertOutput(exec(plotSegments))
}