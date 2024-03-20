package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class LineTest : KandyLetsPlotJupyterTest() {

    private val plotLine = """
        // or use mapOf<String, List<Any>>(...)
        val months by columnOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val sales by columnOf(120, 150, 170, 210, 240, 220, 230, 210, 200, 230, 250, 280)
        val dataset = dataFrameOf(months, sales)

        plot(dataset) {
            line {
                // Positional mapping for the x-axis using months
                x(months)

                // Positional mapping for the y-axis using sales data
                y(sales) {
                    scale = continuous(100..300)
                }

                // Non-positional settings for the line aesthetics
                width = 2.5
                color = Color.RED
                type = LineType.SOLID
                alpha = 0.8
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of line in jupyter`() = plotLine.checkCompilation()

    @Test
    fun `line output in jupyter`() = assertOutput(execRendered(plotLine))
}