package org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces

import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithAlpha
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithFillColor
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithX
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.WithY

/**
 * Interface defining the necessary aesthetics and methods for raster layers.
 *
 * Raster layers are used to represent data in a grid format,
 * commonly used for heatmaps or geographic information systems.
 * The interface provides aesthetics such as `x`, `y`, `fillColor`,
 * and `alpha` for a complete customization of raster representation.
 *
 * Required aesthetics for raster layers are `x` and `y`.
 */
public interface RasterInterface : WithX, WithY,
    WithFillColor, WithAlpha