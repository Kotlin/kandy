package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
public data class Grid(
    val id: String?,
    val show: Boolean?,
    val zlevel: Int?,
    val z: Int?,
    val left: @Contextual Any?,
    val top: @Contextual Any?,
    val right: @Contextual Any?,
    val bottom: @Contextual Any?,
    val width: @Contextual Any?,
    val height: @Contextual Any?,
    val containLabel: Boolean?,
    val backgroundColor: Color?,
    val borderColor: Color?,
    val borderWidth: Int?,
    val shadowBlur: Int?,
    val shadowColor: Color?,
    val shadowOffsetX: Int?,
    val shadowOffsetY: Int,
//    val tooltip: Tooltip?
)
