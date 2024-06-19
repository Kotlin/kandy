package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for rectangle layers.
 *
 * Rectangle layers are typically used for creating visual representations of rectangular shapes,
 * such as in histograms or heatmaps.
 * The interface provides aesthetics including `xMin`, `xMax`, `yMin`,
 * `yMax`, `fillColor`, and `alpha` for complete customization of rectangles.
 *
 * Required aesthetics for rectangle layers are `xMin`, `xMax`, `yMin`, and `yMax`.
 */
public interface RectanglesBuilderInterface : WithXMin, WithXMax,
    WithYMin, WithYMax,
    WithFillColor, WithAlpha, WithXFree, WithYFree