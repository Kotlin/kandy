package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.jupyter.api.Code
import org.jetbrains.kotlinx.jupyter.testkit.JupyterReplTestCase
import org.jetbrains.kotlinx.jupyter.testkit.ReplProvider
import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.letsplot.export.save
import org.jetbrains.kotlinx.kandy.letsplot.feature.coordFlip
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import kotlin.test.Test

abstract class KandyLetsPlotJupyterTest : JupyterReplTestCase(
    ReplProvider.forLibrariesTesting(listOf("kandy-lets-plot"))
)

class CoordFlipTest : KandyLetsPlotJupyterTest() {
    private fun Code.checkCompilation() {
        exec(this)
    }

    @Test
    fun `test coordFlip`() {
        """
            plot {
                bars {
                    x(listOf("a", "b", "c"))
                    y(listOf(4, 3, 5))
                }
                coordFlip()
            }
        """.checkCompilation()
    }

    @Test
    fun test() {
        plot {
            bars {
                x(listOf("a", "b", "c"))
                y(listOf(4, 3, 5))
            }
            coordFlip()
        }.save("test.png")
    }
}