package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.bars
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.layers.points
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import java.io.File
import kotlin.test.*

class SaveTests {
    private val resourcePath = if(System.getProperty("os.name").startsWith("Windows")) {
        "src\\test\\resources\\images"
    } else {
        "src/test/resources/images"
    }
    private val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
    private val barsPlot = plot { bars { x(listOf("first", "second", "third")); y(listOf(5, 1, 4)) } }
    private val pointsPlot =
        plot { points { x(listOf(1.5, 0.7, 2.1)); y(listOf(0.3, 0.15, 0.53)); color(listOf("1", "2", "3")) } }
    private val gridPlot: PlotGrid = plotGrid(listOf(plot, barsPlot, pointsPlot))
    private val bunchPlot: PlotBunch = plotBunch {
        add(plot, 0, 0, 400, 200)
        add(barsPlot, 400, 0, 300, 200)
        add(pointsPlot, 0, 200, 700, 300)
    }

    private fun cleanTestImages() {
        val directory = File(resourcePath)
        if (directory.exists() && directory.isDirectory) {
            directory.listFiles()?.forEach {
                if (it.isFile) {
                    it.delete()
                }
            }
        }
    }

    private fun Plot.testSavePlot(format: String, scale: Double = 1.0, dpi: Int? = null) {
        val outputPath = this.save(filename = "test_plot.$format", scale = scale, dpi = dpi, path = resourcePath)

        val file = File(outputPath)
        assertTrue(file.exists())
        assertEquals(format, file.extension)
        assertTrue(file.parent.endsWith(resourcePath))
    }

    private fun PlotGrid.testSavePlot(format: String, scale: Double = 1.0, dpi: Int? = null) {
        val outputPath = this.save(filename = "test_plot.$format", scale = scale, dpi = dpi, path = resourcePath)

        val file = File(outputPath)
        assertTrue(file.exists())
        assertEquals(format, file.extension)
        assertTrue(file.parent.endsWith(resourcePath))
    }

    private fun PlotBunch.testSavePlot(format: String, scale: Double = 1.0, dpi: Int? = null) {
        val outputPath = this.save(filename = "test_plot.$format", scale = scale, dpi = dpi, path = resourcePath)

        val file = File(outputPath)
        assertTrue(file.exists())
        assertEquals(format, file.extension)
        assertTrue(file.parent.endsWith(resourcePath))
    }

    @BeforeTest
    fun setUp() = cleanTestImages()

    @AfterTest
    fun tearDown() = cleanTestImages()

    @Test
    fun `save plot as svg`() = plot.testSavePlot("svg")

    @Test
    fun `save plot as html`() = plot.testSavePlot("html")

    @Test
    fun `save plot as png`() = plot.testSavePlot("png", scale = 2.0, dpi = 300)

    @Test
    fun `save plot as jpeg`() = plot.testSavePlot("jpeg", scale = 2.5)

    @Test
    fun `save plot as tiff`() = plot.testSavePlot("tiff", dpi = 350)

    @Test
    fun `save plot grid as svg`() = gridPlot.testSavePlot("svg")

    @Test
    fun `save plot grid as html`() = gridPlot.testSavePlot("html")

    @Test
    fun `save plot grid as png`() = gridPlot.testSavePlot("png", scale = 2.0, dpi = 300)

    @Test
    fun `save plot grid as jpeg`() = gridPlot.testSavePlot("jpeg", scale = 2.5)

    @Test
    fun `save plot grid as tiff`() = gridPlot.testSavePlot("tiff", dpi = 350)

    @Test
    fun `save plot bunch as svg`() = bunchPlot.testSavePlot("svg")

    @Test
    fun `save plot bunch as html`() = bunchPlot.testSavePlot("html")

    @Test
    fun `save plot bunch as png`() = bunchPlot.testSavePlot("png", scale = 2.0, dpi = 300)

    @Test
    fun `save plot bunch as jpeg`() = bunchPlot.testSavePlot("jpeg", scale = 2.5)

    @Test
    fun `save plot bunch as tiff`() = bunchPlot.testSavePlot("tiff", dpi = 350)
}