package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class AreaTest : KandyLetsPlotJupyterTest() {

    private val plotArea = """
        plot {
            area {
                // Positional mapping
                x(listOf("January", "February", "March", "April", "May")) {
                    axis.name = "months"
                }
                y(listOf(200, 150, 300, 250, 420)) {
                    axis.name = "sales"
                    scale = continuous(min = 100, max = 500)
                }

                // Non-positional settings
                alpha = 0.5

                // Non-positional mapping with constant fillColor
                fillColor = Color.BLUE

                // BorderLine settings
                borderLine {
                    color = Color.BLACK
                    width = 1.5
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of area in jupyter`() = plotArea.checkCompilation()

    @Test
    fun `area output in jupyter`() = assertOutput(execRendered(plotArea))
}