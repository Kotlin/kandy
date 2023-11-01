package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.intellij.lang.annotations.Language
import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class BoxplotTest : KandyLetsPlotJupyterTest() {

    @Language("kts")
    private val plotBoxplot = """
        plot {
            boxplot {
                // Positional mapping
                x(listOf("A", "B", "C", "D"))
                yMin(listOf(10, 20, 5, 12))
                lower(listOf(20, 30, 12, 22))
                middle(listOf(30, 40, 20, 35))
                upper(listOf(40, 50, 35, 45))
                yMax(listOf(50, 55, 40, 48))

                // Adjust the Y-axis
                y.limits = 0.0..60.0

                // Non-positional settings
                fatten = 0.8
                width = 0.5

                // BorderLine settings
                borderLine.width = .5

                // Non-positional mapping
                fillColor = Color.BLUE
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of boxplot in jupyter`() = plotBoxplot.checkCompilation()

    @Test
    fun `boxplot output in jupyter`() = assertOutput(exec(plotBoxplot))
}