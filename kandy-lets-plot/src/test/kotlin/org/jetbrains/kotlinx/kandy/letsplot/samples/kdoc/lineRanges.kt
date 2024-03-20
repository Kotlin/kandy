package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class LineRangesTest : KandyLetsPlotJupyterTest() {

    private val plotLineRanges = """
        plot {
            lineRanges {
                // Positional mapping
                x(listOf("Jan", "Feb", "Mar", "Apr", "May"))
                yMin(listOf(10, 12, 8, 14, 9))
                yMax(listOf(20, 22, 18, 24, 19))

                // Non-positional settings
                alpha = 0.7
                borderLine {
                    color = Color.GREY
                    width = 2.0
                    type = LineType.DASHED
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of lineRanges in jupyter`() = plotLineRanges.checkCompilation()

    @Test
    fun `lineRanges output in jupyter`() = assertOutput(execRendered(plotLineRanges))
}