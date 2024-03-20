package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class PieTest : KandyLetsPlotJupyterTest() {

    private val plotPie = """
        val categories by columnOf("Rent", "Food", "Utilities", "Transportation", "Entertainment")
        val amounts by columnOf(500.0, 300.0, 150.0, 100.0, 50.0)

        plot {
            pie {
                // Positional settings for the center of the pie chart
                x.constant(0.5) // Assuming this is the normalized center on the X-axis
                y.constant(0.5) // Assuming this is the normalized center on the Y-axis

                slice(amounts) // The values that each slice represents

                // Non-positional setting for the size of the pie chart
                size = 30.0 // Assuming this is a normalized size relative to the plot dimensions

                // Non-positional mapping for the fill color based on the category
                fillColor(categories) {
                    // Inside this block, you would define the mapping parameters or color scale
                    // e.g., you might want to map each category to a specific color
                }

                // Non-positional settings for aesthetics like alpha, stroke, and stroke color
                alpha = 0.8 // Sets the transparency of the pie slices
                stroke = 1.0 // Sets the stroke width around each slice
                strokeColor = Color.BLACK // Sets the color of the stroke around each slice

                // If you want to create a donut chart, set the hole radius
                hole = 0.2 // Assuming this creates a hole with radius equal to 20% of the pie chart radius

                // If you want to "explode" or offset a slice from the center, specify the explode setting
                explode(listOf(.0, .0, 0.1, .0, .0)) // This would offset the "Utilities" slice by 10% of the pie radius
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of pie in jupyter`() = plotPie.checkCompilation()

    @Test
    fun `pie output in jupyter`() = assertOutput(execRendered(plotPie))
}