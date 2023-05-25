package org.jetbrains.kotlinx.kandy.letsplot.util

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

internal fun BufferedImage.toBase64EncodedString(format: String = "png"): String {
    val stream = ByteArrayOutputStream()
    ImageIO.write(this, format, stream)
    val data = stream.toByteArray()
    val encoder = Base64.getEncoder()
    return encoder.encodeToString(data)
}
