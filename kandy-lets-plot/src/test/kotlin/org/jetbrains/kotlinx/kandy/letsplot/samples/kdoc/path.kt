package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class PathTest : KandyLetsPlotJupyterTest() {

    private val plotPath = """
        plot {
            path {
                // Positional mapping
                // Here we would have our mapping parameters to define the scale and possibly transformations
                x(listOf("01:00", "02:00", "03:00", "04:00", "05:00"))
                // Similarly, mapping parameters for the Y-axis
                y(listOf(14, 16, 15, 17, 18))

                // Non-positional settings
                width = 2.0 // Sets the width of the path

                // Non-positional mapping
                // color("type") {
                //      Inside this block, we would define how "type" is mapped to color,
                //      e.g., providing a color scale if "type" is a categorical variable
                // }

                // Here, we specify the line type and alpha if needed
                type = LineType.DOTDASH // Example setting, if your DSL allows direct assignments like this
                alpha = 0.6 // Setting the transparency of the path
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of path in jupyter`() = plotPath.checkCompilation()

    @Test
    fun `path output in jupyter`() = assertOutput(execRendered(plotPath))
}