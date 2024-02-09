package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class TextTest : KandyLetsPlotJupyterTest() {

    private val plotText = """
        plot {
            text {
                // Positional mapping
                x(listOf(1, 2, 3, 4, 5))
                y(listOf(5, 10, 20, 15, 8))

                // Non-positional mapping
                label(listOf("Low", "Medium", "High", "Medium", "Low").toColumn("status"))

                // Non-positional settings
                alpha = 0.8
                font {
                    size = 12.0
                    color = Color.BLUE
                    face = FontFace.BOLD
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of text in jupyter`() = plotText.checkCompilation()

    @Test
    fun `text output in jupyter`() = assertOutput(execRendered(plotText))
}