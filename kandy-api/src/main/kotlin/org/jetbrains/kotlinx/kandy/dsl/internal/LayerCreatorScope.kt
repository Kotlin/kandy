package org.jetbrains.kotlinx.kandy.dsl.internal

/**
 * An abstract class that represents a scope for creating plot layers.
 * Provides default dataset for new layers created in it.
 */
public abstract class LayerCreatorScope {

    /**
     * Parent [PlotBuilder].
     */
    @PublishedApi
    internal abstract val plotBuilder: MultiLayerPlotBuilder
    /**
     * Default dataset index in [MultiLayerPlotBuilder.datasetHandlers].
     */
    internal abstract val datasetIndex: Int
    /**
     * Whether the layers should inherit [MultiLayerPlotBuilder] high-level mappings
     */
    internal abstract val layersInheritMappings: Boolean

    /**
     * Default dataset handler.
     */
    @PublishedApi
    internal val datasetBuilder: DatasetBuilder
        get() = plotBuilder.datasetBuilders[datasetIndex]


    /**
     * Creates a new layer with [LayerBuilder] and adds it to plot.
     */
    public inline fun <T : LayerBuilder> createLayer(builder: T, builderAction: T.() -> Unit) {
        plotBuilder.addLayer(builder.apply(builderAction).toLayer())
    }
}