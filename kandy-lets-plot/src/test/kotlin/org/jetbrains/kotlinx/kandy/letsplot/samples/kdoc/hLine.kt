package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class HLineTest : KandyLetsPlotJupyterTest() {

    private val plotHLine = """
        plot {
            hLine {
                // Set the Y-intercept of the horizontal line
                yIntercept.constant(50.0)

                // Optionally, set the color of the line
                color = Color.RED

                // Set the type or style of the line, such as solid, dashed, or dotted
                type = LineType.DASHED

                // Set the width of the line
                width = 2.0

                // Set the transparency of the line
                alpha = 0.5

                // Even though hLine does not have an "x" attribute, you can still adjust the X-axis limits if needed
                x.axis.limits = 0.0..100.0
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of hLine in jupyter`() = plotHLine.checkCompilation()

    @Test
    fun `hLine output in jupyter`() = assertOutput(execRendered(plotHLine))
}