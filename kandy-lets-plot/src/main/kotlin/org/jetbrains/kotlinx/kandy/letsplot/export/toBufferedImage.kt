/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.export

import jetbrains.datalore.plot.PlotImageExport
import org.jetbrains.kotlinx.kandy.ir.Plot
import org.jetbrains.kotlinx.kandy.letsplot.translator.toLetsPlot
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

// todo grid and bunch