package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for step layers.
 *
 * Step layers are used for step charts,
 * which display data points in a series connected by vertical and horizontal lines.
 * The interface provides aesthetics like `x`, `y`, `alpha`, `color`, `width`, and `lineType` for customization.
 *
 * Required aesthetics for step layers are `x` and `y`.
 */
public interface StepBuilderInterface : WithX, WithY, WithAlpha, WithColor,
    WithWidthAsSize, WithLineType