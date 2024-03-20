package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class StepTest : KandyLetsPlotJupyterTest() {

    private val plotStep = """
        plot {
            step {
                // Positional mapping
                x(listOf(1, 2, 3, 4, 5))
                y(listOf(3, 5, 2, 8, 3))

                // Non-positional mapping
                color = Color.RED

                // Non-positional settings
                width = 2.5
                alpha = 0.7
                lineType = LineType.LONGDASH
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of step in jupyter`() = plotStep.checkCompilation()

    @Test
    fun `step output in jupyter`() = assertOutput(execRendered(plotStep))
}