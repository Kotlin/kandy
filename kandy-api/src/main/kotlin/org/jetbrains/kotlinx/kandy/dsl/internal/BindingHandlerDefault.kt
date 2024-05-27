package org.jetbrains.kotlinx.kandy.dsl.internal

internal class BindingHandlerDefault(
    private val datasetBuilderAccessor: () -> DatasetBuilder
): BindingHandler() {
    override val datasetBuilder: DatasetBuilder
        get() = datasetBuilderAccessor()

    override fun checkMappingSourceSize(size: Int) {
        val rowsCount = datasetBuilder.rowsCount()
        if (rowsCount != size) {
            error("Unexpected size of mapping source: excepted $rowsCount, but received $size")
        }
    }
}
