package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*

/**
 * Interface for defining the necessary aesthetics and methods for tiles layers.
 *
 * Tile layers are typically used to create heatmaps or tile-based visualizations.
 * This interface provides the required aesthetics like `x`, `y`,
 * `alpha`, `fillColor`, `width`, and `height` for customization.
 *
 * The set of required aesthetics for tile layers includes `X` and `Y`.
 */
public interface TilesInterface : WithBorderLine, WithX, WithY, WithAlpha,
    WithFillColor, WithWidth, WithHeight