package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class PointRangesTest : KandyLetsPlotJupyterTest() {

    private val plotPointRanges = """
        val months by columnOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
        val avgTemperatures by columnOf(5.0, 7.0, 10.0, 15.0, 20.0, 25.0)
        val minTemperatures by columnOf(-1.0, 1.0, 3.0, 7.0, 13.0, 18.0)
        val maxTemperatures by columnOf(10.0, 12.0, 16.0, 20.0, 26.0, 30.0)

        plot {
            pointRanges {
                // Positional mapping for the x-axis using months
                x(months)

                // Positional settings for the y-axis using average temperatures
                y(avgTemperatures)

                // Mapping the min and max temperatures for the range
                yMin(minTemperatures)
                yMax(maxTemperatures)

                // Non-positional settings
                alpha = 0.6 // Transparency for the point and range line
                size = 5.0 // Size of the point

                // Customizing the appearance of the inner line and point
                innerLine {
                    type = LineType.LONGDASH // Setting the type of the line for the range
                }

                innerPoint {
                    symbol = Symbol.DIAMOND_FILLED // The symbol used for the point
                    fillColor = Color.RED // The fill color of the symbol
                }

                color = Color.BLUE
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of pointRanges in jupyter`() = plotPointRanges.checkCompilation()

    @Test
    fun `pointRanges output in jupyter`() = assertOutput(exec(plotPointRanges))
}