package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.InnerLine
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.subcontext.InnerPoint

/**
 * Interface defining the necessary aesthetics and methods for pointRange layers.
 *
 * PointRange layers are used to represent points along a range,
 * which is often used in scatter plots, range plots, and other kinds of data visualizations.
 * The interface provides aesthetics such as `x`, `y`, `yMin`, `yMax`, `alpha`, `color`, and `size`.
 * It also introduces `innerPoint` and `innerLine` contexts for more granular customization.
 *
 * Required aesthetics for pointRange layers are `x`, `y`, `yMin`, and `yMax`.
 */
public interface PointRangesInterface : WithX, WithY, WithAlpha,
    WithColor, WithYMin, WithYMax, WithSize {
    public val innerPoint: InnerPoint
    public val innerLine: InnerLine
}