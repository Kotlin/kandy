package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for Pie layers.
 *
 * Pie layers are used to create pie charts, which are often used to represent categorical data.
 * The interface provides aesthetics such as `x`, `y`, `slice`, `explode`, `hole`, `size`,
 * `alpha`, `fillColor`, `stroke`, and `strokeColor` for full customization of the pie chart.
 */
public interface PieInterface : WithX, WithY, WithSlice, WithExplode, WithHole, WithSize,
    WithAlpha, WithFillColor, WithStroke, WithStrokeColor