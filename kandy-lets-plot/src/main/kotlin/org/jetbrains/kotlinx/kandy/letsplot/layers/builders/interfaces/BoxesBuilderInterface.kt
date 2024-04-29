package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for Boxes layers.
 *
 * Boxes are often used to represent the distribution and spread of a dataset.
 * This interface provides various aesthetics like `x`, `alpha`, `fillColor`, `width`,
 * `lower`, `upper`, `middle`, `yMin`, `yMax`, and `fatten` for full customization of the boxes.
 *
 * Required aesthetics for Boxes are `X`, `LOWER`, `UPPER`, `MIDDLE`, `Y_MIN`, and `Y_MAX`.
 */
public interface BoxesBuilderInterface: WithBorderLine, WithX, WithAlpha,
    WithFillColor, WithWidth, WithWhiskerWidth,
    WithLower, WithUpper, WithMiddle, WithYMin, WithYMax, WithFatten, WithYFree