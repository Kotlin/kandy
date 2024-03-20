package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class TilesTest : KandyLetsPlotJupyterTest() {

    private val plotTiles = """
        val xCord by columnOf(1, 1, 2, 2, 3, 3)
        val yCord by columnOf(1, 2, 1, 2, 1, 2)
        val value by columnOf(5, 10, 15, 20, 25, 30)

        plot {
            tiles {
                // Positional mapping
                x(xCord)
                y(yCord)

                // Non-positional mapping
                fillColor(value) {
                    scale = continuousColorGradient2(Color.WHITE, Color.BLUE, Color.RED, 0.5)
                }

                // Non-positional settings
                alpha = 0.8
                borderLine {
                    color = Color.BLACK
                    width = 0.5
                }

                width = 0.9
                height = 0.9
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of tiles in jupyter`() = plotTiles.checkCompilation()

    @Test
    fun `tiles output in jupyter`() = assertOutput(execRendered(plotTiles))
}