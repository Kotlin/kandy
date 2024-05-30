package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.Layer

/**
 * Represents the configuration context for a layer within a plot,
 * managing its aesthetic mappings, settings, and associated features.
 * The `LayerBuilder` provides a mechanism
 * to define how data should be visually presented in a layer and how it interacts with the overall plot.
 */
public sealed interface LayerBuilder {
    /**
     * Returns a new [Layer] configured by this builder.
     */
    public fun toLayer(): Layer
}