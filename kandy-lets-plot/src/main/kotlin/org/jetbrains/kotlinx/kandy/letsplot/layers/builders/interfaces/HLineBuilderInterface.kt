package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for hLine layers.
 *
 * hLine layers are used to add a horizontal line at a specific yIntercept on a plot.
 * The interface provides aesthetics such as `yIntercept`, `alpha`, `color`, `width`, `type`,
 * `x` to allow for full customization of the horizontal lines.
 *
 * Required aesthetics for hLine are `yIntercept`.
 */
public interface HLineBuilderInterface : WithYIntercept, WithAlpha, WithColor,
    WithWidthAsSize, WithType, WithXFree