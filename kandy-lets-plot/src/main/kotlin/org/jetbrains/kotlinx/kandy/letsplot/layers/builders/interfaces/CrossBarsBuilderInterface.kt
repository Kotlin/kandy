package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for CrossBars layers.
 *
 * CrossBars are used to represent a central tendency measure and spread of a dataset
 * through horizontal lines crossing the bars.
 * This interface provides various aesthetics like `x`, `y`, `yMin`, `yMax`, `fatten`, `width`,
 * `fillColor`, and `alpha` to enable full customization.
 *
 * Required aesthetics for CrossBars are `X`, `Y`, `Y_MIN`, `Y_MAX`.
 */
public interface CrossBarsBuilderInterface : WithBorderLine, WithX, WithY, WithYMin, WithYMax,
    WithFatten, WithWidth, WithFillColor, WithAlpha