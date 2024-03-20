package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.plotGrid
import org.junit.Assert.assertNotEquals
import kotlin.test.Test
import kotlin.test.assertNotNull

class BufferedImageTest {
    @Test
    fun `export plot as BufferedImage`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val scale = 2.0
        val dpi = 300

        val bufferedImage = plot.toBufferedImage(scale, dpi)

        assertNotNull(bufferedImage)

        val bufferedImageWithDifferentScale = plot.toBufferedImage(1.0, dpi)
        assertNotEquals(bufferedImage.width, bufferedImageWithDifferentScale.width)
        assertNotEquals(bufferedImage.height, bufferedImageWithDifferentScale.height)
    }

    @Test
    fun `export plot as JPG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val scale = 2.0
        val dpi = 300

        val jpgImage = plot.toJPG(scale, dpi)

        assertNotNull(jpgImage)

        val jpgImageWithDifferentScale = plot.toJPG(1.0, dpi)
        assertNotEquals(jpgImage, jpgImageWithDifferentScale)
    }

    @Test
    fun `export plot as PNG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val scale = 2.0
        val dpi = 300

        val pngImage = plot.toPNG(scale, dpi)

        assertNotNull(pngImage)

        val pngImageWithDifferentScale = plot.toPNG(1.0, dpi)
        assertNotEquals(pngImage, pngImageWithDifferentScale)
    }

    @Test
    fun `export plot grid as BufferedImage`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val grid = plotGrid(listOf(plot, plot, plot))
        val scale = 2.0
        val dpi = 300

        val bufferedImage = grid.toBufferedImage(scale, dpi)

        assertNotNull(bufferedImage)

        val bufferedImageWithDifferentScale = grid.toBufferedImage(1.0, dpi)
        assertNotEquals(bufferedImage.width, bufferedImageWithDifferentScale.width)
        assertNotEquals(bufferedImage.height, bufferedImageWithDifferentScale.height)
    }

    @Test
    fun `export plot grid as JPG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val grid = plotGrid(listOf(plot, plot, plot))
        val scale = 2.0
        val dpi = 300

        val jpgImage = grid.toJPG(scale, dpi)

        assertNotNull(jpgImage)

        val jpgImageWithDifferentScale = grid.toJPG(1.0, dpi)
        assertNotEquals(jpgImage, jpgImageWithDifferentScale)
    }

    @Test
    fun `export plot grid as PNG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val grid = plotGrid(listOf(plot, plot, plot))
        val scale = 2.0
        val dpi = 300

        val pngImage = grid.toPNG(scale, dpi)

        assertNotNull(pngImage)

        val pngImageWithDifferentScale = grid.toPNG(1.0, dpi)
        assertNotEquals(pngImage, pngImageWithDifferentScale)
    }

    @Test
    fun `export plot bunch as BufferedImage`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val bunch = plotBunch {
            add(plot, 0, 0)
            add(plot, 400, 600)
            add(plot, 800, 600)
        }
        val scale = 2.0
        val dpi = 300

        val bufferedImage = bunch.toBufferedImage(scale, dpi)

        assertNotNull(bufferedImage)

        val bufferedImageWithDifferentScale = bunch.toBufferedImage(1.0, dpi)
        assertNotEquals(bufferedImage.width, bufferedImageWithDifferentScale.width)
        assertNotEquals(bufferedImage.height, bufferedImageWithDifferentScale.height)
    }

    @Test
    fun `export plot bunch as JPG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val bunch = plotBunch {
            add(plot, 0, 0)
            add(plot, 400, 600)
            add(plot, 800, 600)
        }
        val scale = 2.0
        val dpi = 300

        val jpgImage = bunch.toJPG(scale, dpi)

        assertNotNull(jpgImage)

        val jpgImageWithDifferentScale = bunch.toJPG(1.0, dpi)
        assertNotEquals(jpgImage, jpgImageWithDifferentScale)
    }

    @Test
    fun `export plot bunch as PNG`() {
        val plot: Plot = plot { line { x(listOf(1, 2, 3)); y(listOf(0.3, 0.15, 0.53)) } }
        val bunch = plotBunch {
            add(plot, 0, 0)
            add(plot, 400, 600)
            add(plot, 800, 600)
        }
        val scale = 2.0
        val dpi = 300

        val pngImage = bunch.toPNG(scale, dpi)

        assertNotNull(pngImage)

        val pngImageWithDifferentScale = bunch.toPNG(1.0, dpi)
        assertNotEquals(pngImage, pngImageWithDifferentScale)
    }
}