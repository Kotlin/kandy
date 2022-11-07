package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
public data class Polar(
    val id: String?,
    val zlevel: Int?,
    val z: Int?,
    val center: List<@Contextual Any>?, // ['50%', '50%']
    val radius: @Contextual Any?, // number string array
//    val tooltip: Tooltip?
)
