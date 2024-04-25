package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for line layers.
 *
 * Line layers are often used to connect data points in the order in which they appear in the data frame.
 * The interface provides aesthetics such as `x`, `y`,
 * `alpha`, and `color` to allow for full customization of the lines.
 *
 * Required aesthetics for line are `x` and `y`.
 */
public interface LineBuilderInterface: WithX, WithY, WithAlpha, WithColor,
    WithWidthAsSize, WithType