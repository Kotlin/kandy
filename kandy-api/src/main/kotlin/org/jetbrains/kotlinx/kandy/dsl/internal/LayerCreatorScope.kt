package org.jetbrains.kotlinx.kandy.dsl.internal

/**
 * An abstract class that represents a context for collecting layers in a plot.
 *
 * @property createLayer TODO
 */
public abstract class LayerCreatorScope {
    // TODO(waiting for Context Parameters release https://github.com/Kotlin/KEEP/issues/367)
    // should not be member, temporary workaround until context parameters are ready
    @PublishedApi
    internal abstract val plotBuilder: MultiLayerPlotBuilder
    internal abstract val datasetIndex: Int
    internal abstract val layersInheritMappings: Boolean
    @PublishedApi
    internal val datasetHandler: DatasetHandler
        get() = plotBuilder.datasetHandlers[datasetIndex]

    // TODO(waiting for Context Parameters release https://github.com/Kotlin/KEEP/issues/367)
    // should create and return Layer (not add it) and be used with [MultiLayerPlotBuilder.addLayer]
    public inline fun <T : LayerBuilder> createLayer(builder: T, builderAction: T.() -> Unit) {
        plotBuilder.addLayer(builder.apply(builderAction).toLayer())
    }
}