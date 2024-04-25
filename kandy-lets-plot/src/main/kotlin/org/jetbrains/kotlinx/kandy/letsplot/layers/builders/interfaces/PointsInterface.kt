package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for points layers.
 *
 * Points layers are primarily used in scatter plots to represent individual data points in two-dimensional space.
 * The interface provides aesthetics such as `x`, `y`, `color`, `symbol`, `size`, `alpha`, `stroke`, and `fillColor`
 * to allow for a comprehensive customization of point representation.
 *
 * Required aesthetics for points layers are `x` and `y`.
 */
public interface PointsInterface : WithX, WithY, WithColor, WithSymbol,
    WithSize, WithAlpha, WithFillColor, WithPointStroke