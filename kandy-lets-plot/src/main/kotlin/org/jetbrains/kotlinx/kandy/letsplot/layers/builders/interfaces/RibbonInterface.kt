package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for ribbon layers.
 *
 * Ribbon layers are generally used to visualize a range around a line, such as confidence intervals.
 * This interface provides aesthetics like `x`, `yMin`, `yMax`, `fillColor`, and `alpha` for customization.
 *
 * Required aesthetics for ribbon layers are `x`, `yMin`, and `yMax`.
 */
public interface RibbonInterface : WithX, WithYMin,
    WithYMax, WithFillColor, WithAlpha, WithYFree