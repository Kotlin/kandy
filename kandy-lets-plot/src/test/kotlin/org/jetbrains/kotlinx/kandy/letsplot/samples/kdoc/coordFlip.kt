package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class CoordFlipTest : KandyLetsPlotJupyterTest() {

    private val plotCoordFlip = """
            plot {
                bars {
                    x(listOf("a", "b", "c"))
                    y(listOf(4, 3, 5))
                }
                coordFlip()
            }
        """.trimIndent()

    @Test
    fun `compilation of coordFlip in jupyter`() = plotCoordFlip.checkCompilation()

    @Test
    fun `coordFlip output in jupyter`() = assertOutput(execRendered(plotCoordFlip))
}