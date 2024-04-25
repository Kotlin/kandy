package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface defining the necessary aesthetics and methods for ErrorBars layers.
 *
 * ErrorBars are used to represent the variability of data in scatter and line plots.
 * This interface provides various aesthetics like `x`, `yMin`, `yMax`, `width`, `alpha`,
 * and `y` to enable full customization.
 *
 * Required aesthetics for ErrorBars are `X`, `Y_MIN`, and `Y_MAX`.
 */
public interface ErrorBarsInterface : WithX, WithYMin,
    WithYMax, WithWidth, WithAlpha, WithYFree