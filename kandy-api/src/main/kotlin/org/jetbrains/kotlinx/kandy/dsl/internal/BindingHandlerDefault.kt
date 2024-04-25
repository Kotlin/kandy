package org.jetbrains.kotlinx.kandy.dsl.internal

internal class BindingHandlerDefault(
    private val datasetHandlerAccessor: () -> DatasetHandler
): BindingHandler() {
    override val datasetHandler: DatasetHandler
        get() = datasetHandlerAccessor()
}
