package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for ABLine layers.
 *
 * ABLine layers are often used to add a line with a specific slope and intercept to a plot.
 * The interface inherits provides aesthetics: `type`, `color`, `alpha`, `width`, `slope`, `intercept`, `x`, `y`,
 * to allow customization.
 *
 * Required aesthetics for ABLine are `SLOPE` and `INTERCEPT`.
 */
public interface ABLineBuilderInterface : WithType,
    WithColor, WithAlpha,
    WithWidthAsSize, WithSlope, WithIntercept,
    WithXFree, WithYFree