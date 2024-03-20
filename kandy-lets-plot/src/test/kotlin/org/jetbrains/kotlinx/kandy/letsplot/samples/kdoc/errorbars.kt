package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class ErrorBarsTest : KandyLetsPlotJupyterTest() {

    private val plotErrorBars = """
        plot {
            errorBars {
                // Positional mapping
                x(listOf("Group A", "Group B", "Group C", "Group D"))
                yMin(listOf(48.0, 33.0, 20.0, 22.0))
                yMax(listOf(75.0, 88.0, 60.0, 68.0))

                // Adjust the Y-axis
                y.limits = 0.0..100.0

                // Non-positional settings
                alpha = 0.8

                // BorderLine settings
                borderLine {
                    color(listOf("A", "B", "A", "B"))
                    width = 1.0
                    type = LineType.SOLID
                }
                // Non-positional mapping
                width = 0.2
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of errorBars in jupyter`() = plotErrorBars.checkCompilation()

    @Test
    fun `errorBars output in jupyter`() = assertOutput(execRendered(plotErrorBars))
}