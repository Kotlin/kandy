package org.jetbrains.kotlinx.kandy.letsplot.samples.kdoc

import org.jetbrains.kotlinx.kandy.letsplot.jupyter.KandyLetsPlotJupyterTest
import kotlin.test.Test

class PointsTest : KandyLetsPlotJupyterTest() {

    private val plotPoints = """
        val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
        val sales = listOf(10000, 15000, 18000, 25000, 22000, 20000)
        val customerCounts = listOf(80, 120, 150, 200, 180, 160)

        plot {
            points {
                // Positional mapping
                x(months) // Categories on the x-axis
                y(sales) // Numerical values on the y-axis

                // Non-positional settings
                color = Color.BLUE // Set a constant color for the points
                alpha = 0.7 // Set a constant transparency for the points
                fillColor = Color.GREEN // Set a fill color for the points (for filled symbols)

                // Map 'customerCounts' to 'size' to represent the number of customers as the size of the point
                size(customerCounts) {
                    // Additional mapping parameters if necessary
                    // For example, you might want to normalize or scale the sizes
                }
            }
        }
        """.trimIndent()

    @Test
    fun `compilation of points in jupyter`() = plotPoints.checkCompilation()

    @Test
    fun `points output in jupyter`() = assertOutput(execRendered(plotPoints))
}