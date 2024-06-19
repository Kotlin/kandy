package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.Plot

/**
 * Represents the high-level context for creating and configuring a [Plot].
 *
 * The `PlotBuilder` provides a structured environment to define and manage datasets,
 * features, and other aesthetic bindings essential for generating a plot.
 * It acts as an intermediary between raw data and the final visual representation
 * by aggregating all necessary parts and then allowing the creation of a [Plot] using the `toPlot` method.
 */
public sealed interface PlotBuilder {
    /**
     * Returns a new [Plot] configured by this builder.
     */
    public fun toPlot(): Plot
}
