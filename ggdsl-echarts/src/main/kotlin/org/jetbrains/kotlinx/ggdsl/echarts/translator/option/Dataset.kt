package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Serializable

@Serializable
internal data class Dataset(
    val id: String? = null,
    val source: List<List<String>>? = null, // TODO
    val sourceHeader: Boolean? = null, // number?
//    val transform: Transform? = null,
    val fromDatasetIndex: Int? = null,
    val fromDatasetId: String? = null,
    val fromTransformResult: Int? = null
)
