package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class RectanglesTest : KandyLetsPlotJupyterTest() {

    private val plotRectangles = """
        val startTime by columnOf(1, 2, 3)
        val endTime by columnOf(2, 3.5, 4)
        val startCnt by columnOf(5.1, 2.3, 3.7)
        val endCnt by columnOf(5.3, 2.4, 4.3)
        val event by columnOf("Event A", "Event B", "Event C")

        // Now, let's visualize the duration of each event as a rectangle.
        plot {
            rectangles {
                // Map the start and end times to the X-axis to create the rectangles
                xMin(startTime)
                xMax(endTime)
                // Map the start and end counts to the Y-axis
                yMin(startCnt)
                yMax(endCnt)

                // Set the fill color based on the event
                fillColor(event) {
                    // Additional scale parameters can be set here if needed
                }

                // Set transparency and border properties for the rectangles
                alpha = 0.7
                borderLine {
                    color = Color.GREY
                    width = 1.0
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of rectangles in jupyter`() = plotRectangles.checkCompilation()

    @Test
    fun `rectangles output in jupyter`() = assertOutput(exec(plotRectangles))
}