package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class CrossBarsTest : KandyLetsPlotJupyterTest() {

    private val plotCrossBars = """
        plot {
            crossBars {
                // Positional mapping
                x(listOf("A", "B", "C", "D"))
                y(listOf(25.0, 30.0, 22.0, 35.0))
                yMin(listOf(10.0, 15.0, 5.0, 20.0))
                yMax(listOf(40.0, 35.0, 30.0, 50.0))

                // Adjust the Y-axis
                y.limits = 0.0..55.0

                // Non-positional settings
                fatten = 1.2
                width = 0.6

                // BorderLine settings
                borderLine {
                    color = Color.BLACK
                    width = 1.5
                    type = LineType.SOLID
                }

                // Non-positional mapping
                fillColor(listOf("red", "green", "blue", "yellow")) {
                    scale = categorical(
                        "red" to Color.RED, "green" to Color.GREEN,
                        "blue" to Color.BLUE, "yellow" to Color.YELLOW
                    )
                }
            }
        }
        """.trimIndent()


    @Test
    fun `compilation of crossBars in jupyter`() = plotCrossBars.checkCompilation()

    @Test
    fun `crossBars output in jupyter`() = assertOutput(exec(plotCrossBars))
}