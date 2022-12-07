package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class LabelLayout(
    val hideOverlap: Boolean? = null,
    val moveOverlap: String? = null, // Todo
    val x: String? = null,
    val y: String? = null,
    val dx: Int? = null,
    val dy: Int? = null,
    val rotate: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
    val align: String? = null,
    val verticalAlign: String? = null,
    val fontSize: Int? = null,
    val draggable: Boolean? = null,
    val labelLinePoints: Triple<Pair<String, String>, Pair<String, String>, Pair<String, String>>? = null
)