package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.intellij.lang.annotations.Language
import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class LayoutTest : KandyLetsPlotJupyterTest() {

    @Language("kts")
    private val plotLayout = """
                    plot {
                        line { x(listOf(1, 2, 3)); y.constant(5) }
                        layout {
                            title = "Main Title"
                            subtitle = "Subtitle"
                            xAxisLabel = "X-Axis"
                            yAxisLabel = "Y-Axis"
                            style(Style.Grey)
                        }
                    }
                    """.trimIndent()

    @Test
    fun `compilation of layout in jupyter`() = plotLayout.checkCompilation()

    @Test
    fun `layout output in jupyter`() = assertOutput(exec(plotLayout))
}