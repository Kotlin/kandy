package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for Area layers.
 *
 * Area layers are used to represent the space between a line and a baseline, often to indicate volume or
 * quantities over a range of values.
 * The interface provides aesthetics: `x`, `y`, `alpha`, `fillColor`, to allow customization.
 *
 * Required aesthetics for Area are `X` and `Y`.
 */
public interface AreaBuilderInterface : WithBorderLine, WithX, WithY, WithAlpha, WithFillColor