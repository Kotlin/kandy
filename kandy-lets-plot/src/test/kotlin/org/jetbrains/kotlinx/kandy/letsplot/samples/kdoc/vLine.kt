package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class VLineTest : KandyLetsPlotJupyterTest() {

    private val plotVLine = """
        plot {
            vLine {
                // Set the X-intercept of the vertical line
                xIntercept.constant(10.0)

                // Optionally, set the color of the line
                color = Color.GREEN

                // Set the type or style of the line, such as solid, dashed, or dotted
                type = LineType.SOLID

                // Set the width of the line
                width = 3.0

                // Set the transparency of the line
                alpha = 0.7

                // Although vLine does not have a "y" attribute, you can adjust the Y-axis limits if needed
                y.limits = 0.0..30.0
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of vLine in jupyter`() = plotVLine.checkCompilation()

    @Test
    fun `vLine output in jupyter`() = assertOutput(exec(plotVLine))
}