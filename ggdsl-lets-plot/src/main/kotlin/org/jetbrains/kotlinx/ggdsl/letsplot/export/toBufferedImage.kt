package org.jetbrains.kotlinx.ggdsl.letsplot.export

import jetbrains.datalore.plot.PlotImageExport
import org.jetbrains.kotlinx.ggdsl.ir.Plot
import org.jetbrains.kotlinx.ggdsl.letsplot.translator.toLetsPlot
import org.jetbrains.letsPlot.intern.toSpec
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

public fun Plot.toBufferedImage(
    scale: Number = 1,
    dpi: Number? = null,
): BufferedImage {
    val byteArray = PlotImageExport.buildImageFromRawSpecs(
        this.toLetsPlot().toSpec(),
        PlotImageExport.Format.PNG,
        scale.toDouble(), dpi?.toDouble() ?: Double.NaN
    ).bytes
    return ImageIO.read(ByteArrayInputStream(byteArray))
}
