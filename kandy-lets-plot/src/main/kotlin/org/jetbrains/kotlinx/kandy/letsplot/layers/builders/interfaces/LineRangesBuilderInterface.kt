package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for lineRanges layers.
 *
 * LineRanges layers are used to display a range between a minimum and maximum y-value at each x-coordinate.
 * The interface provides aesthetics such as `x`, `yMin`, `yMax`, and `alpha` for full customization of the line ranges.
 *
 * Required aesthetics for lineRanges are `x`, `yMin`, and `yMax`.
 */
public interface LineRangesBuilderInterface : WithX, WithYMin,
    WithYMax, WithAlpha, WithYFree