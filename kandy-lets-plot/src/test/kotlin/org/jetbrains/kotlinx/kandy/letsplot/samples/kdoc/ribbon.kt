package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class RibbonTest : KandyLetsPlotJupyterTest() {

    private val plotRibbon = """
        val year by columnOf(2000, 2001, 2002, 2003, 2004)
        val level by columnOf(2.0, 3.5, 6.0, 4.3, 5.1)

        plot {
            ribbon {
                // Map 'year' for x-axis, which specifies the line along which the ribbon is drawn
                x(year) {
                    scale = continuous(1999..2005)
                }

                // Map `level -1` and `level + 1` for the y-axis to specify the bounds of the ribbon
                yMin(level.map { it - 1 })
                yMax(level.map { it + 1 })

                // Set the fill color
                // fillColor("category") {
                // Here, you can set the scale and legend parameters as needed
                // }

                // Set the transparency of the ribbon
                alpha = 0.5

                // Set the properties for the border of the ribbon
                borderLine {
                    color = Color.RED
                    width = 1.0
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of ribbon in jupyter`() = plotRibbon.checkCompilation()

    @Test
    fun `ribbon output in jupyter`() = assertOutput(exec(plotRibbon))
}