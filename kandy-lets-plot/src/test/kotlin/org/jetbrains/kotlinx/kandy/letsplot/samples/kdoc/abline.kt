package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.intellij.lang.annotations.Language
import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class ABLineTest : KandyLetsPlotJupyterTest() {

    @Language("kts")
    private val plotABLine = """
        plot {
            abLine {
                // Map 'time' variable to intercept aesthetic
                intercept(listOf(.1, .2, .3, .4, .5))
                // Set a constant slope
                slope.constant(0.5)
                // Set a constant width for the line
                width = 2.5
                // Map 'type' variable to color aesthetic
                color(listOf("A", "A", "B", "B", "C")) {
                    // Additional mapping parameters, e.g., you can specify a color palette here
                    scale = categorical("A" to Color.RED, "B" to Color.PURPLE, "C" to Color.BLUE)
                }
                // Set alpha (transparency) of the line
                alpha = 0.7
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of abLine in jupyter`() = plotABLine.checkCompilation()

    @Test
    fun `abLine HTML output in jupyter`() = assertOutput(exec(plotABLine))
}