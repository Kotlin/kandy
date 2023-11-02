package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.intellij.lang.annotations.Language
import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class BarsTest : KandyLetsPlotJupyterTest() {

    @Language("kts")
    private val plotBars = """
        plot {
            bars {
                // Positional mapping
                x(listOf("Apple", "Banana", "Cherry", "Orange", "Strawberry"))
                y(listOf(5.0, 7.5, 3.0, 4.5, 6.0)) {
                    axis.breaks((0..16).map { it / 2.0 })
                }

                // Non-positional settings
                alpha = 0.7
                width = 0.4

                // BorderLine settings
                borderLine {
                    color = Color.BLACK
                    width = 2.5
                    type = LineType.DASHED
                }

                // Non-positional mapping
                fillColor(listOf("a", "b", "b", "c", "a"))
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of bars in jupyter`() = plotBars.checkCompilation()

    @Test
    fun `bars output in jupyter`() = assertOutput(exec(plotBars))


    @Language("kts")
    private val plotBarsH = """
        plot {
            barsH {
                // Positional mapping
                y(listOf("Apple", "Banana", "Cherry", "Orange", "Strawberry"))
                x(listOf(5.0, 7.5, 3.0, 4.5, 6.0)) {
                    axis.breaks((0..16).map { it / 2.0 })
                }

                // Non-positional settings
                alpha = 0.7
                width = 0.4

                // BorderLine settings
                borderLine {
                    color = Color.BLACK
                    width = 2.5
                    type = LineType.DASHED
                }

                // Non-positional mapping
                fillColor(listOf("a", "b", "b", "c", "a"))
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of barsH in jupyter`() = plotBarsH.checkCompilation()

    @Test
    fun `barsH output in jupyter`() = assertOutput(exec(plotBarsH))
}