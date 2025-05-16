package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.kandy.echarts.translator.option.util.Element
import org.jetbrains.kotlinx.kandy.echarts.translator.serializers.DataElementListSerializer

@Serializable
internal data class Dataset(
    val id: String? = null,
    @Serializable(with = DataElementListSerializer::class)
    val source: List<List<Element?>>? = null,
    val sourceHeader: Boolean? = null, // number?
//    val transform: Transform? = null,
    val fromDatasetIndex: Int? = null,
    val fromDatasetId: String? = null,
    val fromTransformResult: Int? = null
) {

    fun isEmpty(): Boolean =
        id == null && source?.all { it.isEmpty() } ?: true && sourceHeader == null && fromDatasetIndex == null
                && fromDatasetId == null && fromTransformResult == null

    fun isNotEmpty(): Boolean = !this.isEmpty()
}
