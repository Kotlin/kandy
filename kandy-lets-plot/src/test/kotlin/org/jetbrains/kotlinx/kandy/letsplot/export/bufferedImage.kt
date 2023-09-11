package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.dsl.plot
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.layers.line
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
}