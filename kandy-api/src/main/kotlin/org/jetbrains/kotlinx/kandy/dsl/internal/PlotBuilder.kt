package org.jetbrains.kotlinx.kandy.dsl.internal

import org.jetbrains.kotlinx.kandy.ir.Plot

/**
 * Represents the high-level context for creating and configuring a [Plot].
 *
 * The `PlotContext` provides a structured environment to define and manage datasets,
 * features, and other aesthetic bindings essential for generating a plot.
 * It acts as an intermediary between raw data and the final visual representation
 * by aggregating all necessary components and then allowing the creation of a [Plot] using the `toPlot` method.
 *
 * @property datasetHandlers A mutable list to store and manage various dataset handlers,
 * which help in processing and translating raw data sources into visual representations.
 * @property plotFeatures A mutable map associating feature names to their respective plot features,
 * enabling custom visual enhancements and modifications on the plot.
 */
public sealed interface PlotBuilder {
    public fun toPlot(): Plot
}
