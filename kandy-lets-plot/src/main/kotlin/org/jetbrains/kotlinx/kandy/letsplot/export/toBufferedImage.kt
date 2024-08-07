/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.export

import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotBunch
import org.jetbrains.kotlinx.kandy.letsplot.multiplot.model.PlotGrid
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
import org.jetbrains.kotlinx.kandy.letsplot.translator.wrap
import org.jetbrains.letsPlot.core.plot.export.PlotImageExport
import org.jetbrains.letsPlot.intern.toSpec
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

/**
 * Convert plot spec to `BufferedImage`
 *
 * @receiver the plot spec represented as `MutableMap`
 */
internal fun MutableMap<String, Any>.toBufferedImage(
    scale: Number = 1,
    dpi: Number? = null
): BufferedImage {
    val byteArray = PlotImageExport.buildImageFromRawSpecs(
        this,
        PlotImageExport.Format.PNG,
        scale.toDouble(), dpi?.toDouble() ?: Double.NaN
    ).bytes
    return ImageIO.read(ByteArrayInputStream(byteArray))
}

/**
 * Exports the current plot as a [BufferedImage].
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [Plot] - the plot to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [BufferedImage] the created image representing the plot.
 */
public fun Plot.toBufferedImage(
    scale: Number = 1,
    dpi: Number? = null,
): BufferedImage = this.toLetsPlot().toSpec().toBufferedImage(scale, dpi)

/**
 * Exports the current plot grid as a [ByteArray] in the JPG format.
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [Plot] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [ByteArray] the created image representing the plot grid.
 */

public fun Plot.toJPG(
    scale: Number = 1,
    dpi: Number? = null,
): ByteArray {
    val bufferedImage = this.toLetsPlot().toSpec().toBufferedImage(scale, dpi)
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "JPEG", outputStream)
    return outputStream.toByteArray()
}

/**
 * Exports the current plot grid as a [ByteArray] in the PNG format.
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [Plot] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [ByteArray] the created image representing the plot grid.
 */

public fun Plot.toPNG(
    scale: Number = 1,
    dpi: Number? = null,
): ByteArray {
    val bufferedImage = this.toLetsPlot().toSpec().toBufferedImage(scale, dpi)
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "PNG", outputStream)
    return outputStream.toByteArray()
}

/**
 * Exports the current plot grid as a [BufferedImage].
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [PlotGrid] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [BufferedImage] the created image representing the plot grid.
 */
public fun PlotGrid.toBufferedImage(
    scale: Number = 1,
    dpi: Number? = null,
): BufferedImage = this.wrap().toSpec().toBufferedImage(scale, dpi)

/**
 * Exports the current plot grid as a [ByteArray] in the JPG format.
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [PlotGrid] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [ByteArray] the created image representing the plot grid.
 */

public fun PlotGrid.toJPG(
    scale: Number = 1,
    dpi: Number? = null,
): ByteArray {
    val bufferedImage = this.wrap().toSpec().toBufferedImage(scale, dpi)
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "JPEG", outputStream)
    return outputStream.toByteArray()
}

/**
 * Exports the current plot grid as a [ByteArray] in the PNG format.
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [PlotGrid] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [ByteArray] the created image representing the plot grid.
 */

public fun PlotGrid.toPNG(
    scale: Number = 1,
    dpi: Number? = null,
): ByteArray {
    val bufferedImage = this.wrap().toSpec().toBufferedImage(scale, dpi)
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "PNG", outputStream)
    return outputStream.toByteArray()
}

/**
 * Exports the current plot bunch as a [BufferedImage].
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [PlotBunch] - the plot bunch to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [BufferedImage] the created image representing the plot bunch.
 */
public fun PlotBunch.toBufferedImage(
    scale: Number = 1,
    dpi: Number? = null,
): BufferedImage = this.wrap().toSpec().toBufferedImage(scale, dpi)

/**
 * Exports the current plot grid as a [ByteArray] in the JPG format.
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [PlotBunch] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [ByteArray] the created image representing the plot grid.
 */

public fun PlotBunch.toJPG(
    scale: Number = 1,
    dpi: Number? = null,
): ByteArray {
    val bufferedImage = this.wrap().toSpec().toBufferedImage(scale, dpi)
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "JPEG", outputStream)
    return outputStream.toByteArray()
}

/**
 * Exports the current plot grid as a [ByteArray] in the PNG format.
 *
 * The parameters [scale] and [dpi] influence the quality and size of the rasterized image.
 *
 * @receiver [PlotBunch] - the plot grid to export.
 * @param scale the scaling is applied to the plot when converting to a raster format (PNG).
 * It affects the resolution and size of the resulting [BufferedImage].
 * The default value is 1.
 * @param dpi the resolution of the exported image in dots per inch (DPI).
 * This parameter influences the quality of the rasterized image, with a higher value resulting in better quality.
 * By default, no specific DPI value is assigned, and it utilizes the system's default settings.
 * @return [ByteArray] the created image representing the plot grid.
 */

public fun PlotBunch.toPNG(
    scale: Number = 1,
    dpi: Number? = null,
): ByteArray {
    val bufferedImage = this.wrap().toSpec().toBufferedImage(scale, dpi)
    val outputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "PNG", outputStream)
    return outputStream.toByteArray()
}