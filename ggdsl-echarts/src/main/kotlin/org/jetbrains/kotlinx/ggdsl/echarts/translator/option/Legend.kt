package org.jetbrains.kotlinx.ggdsl.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
public enum class LegendType(public val type: String) {
    PLAIN("plain"), SCROLL("scroll")
}

@Serializable
public enum class Orient(public val type: String) {
    HORIZONTAL("horizontal"), VERTICAL("vertical")
}

@Serializable
public data class Legend(
    val type: LegendType?, /* для scroll можно использовать
legend.scrollDataIndex
legend.pageButtonItemGap
legend.pageButtonGap
legend.pageButtonPosition
legend.pageFormatter
legend.pageIcons
legend.pageIconColor
legend.pageIconInactiveColor
legend.pageIconSize
legend.pageTextStyle
legend.animation
legend.animationDurationUpdate
     */
    val id: String?,
    val show: Boolean?,
    val zlevel: Int?,
    val z: Int?,
    val left: @Contextual Any?, // string number
    val top: @Contextual Any?,
    val right: @Contextual Any?,
    val bottom: @Contextual Any?,
    val width: @Contextual Any?, // string number
    val height: @Contextual Any?,
    val orient: Orient?,
    val align: String?, // 'auto', 'left', 'right'
    val padding: @Contextual Any?, // number array
    val itemGap: Int?,
    val itemWidth: Int?,
    val itemHeight: Int?,
//    val itemStyle: ItemStyle?,
//    val lineStyle: LineStyle?,
    val symbolRotate: @Contextual Any?, // number string
    val formatter: @Contextual Any?, // может быть функцией, надо подумать
    val selectedMode: @Contextual Any?, // boolean string
    val inactiveColor: Color?,
    val inactiveBorderColor: Color?,
    val inactiveBorderWidth: Color?,
    val selected: Map<String, Boolean>?, // таблица состояний для серий
//    val textStyle: TextStyle?,
//    val tooltip: Tooltip?,
    val icon: String?, // может быть ссылкой
//    val data: List<LegendData>?,
    val backgroundColor: Color?,
    val borderColor: Color?,
    val borderRadius: @Contextual Any?, // number Array
    val shadowBlur: Int?,
    val shadowColor: Color?,
    val shadowOffsetX: Int?,
    val shadowOffsetY: Int?, // This property works only if show: true configured.
    val scrollDataIndex: Int?,
    val pageButtonItemGap: Int?,
    val pageButtonGap: Int?,
    val pageButtonPosition: String?, // Start End
    val pageFormatter: Int?, // function???
    val pageIcons: List<@Contextual Any>?, // ????
    val pageIconColor: String?,
    val pageIconInactiveColor: String?,
    val pageIconSize: @Contextual Any?,
//    val pageTextStyle: TextStyle?,
    val animation: Boolean?,
    val animationDurationUpdate: Int?,
//    val emphasis: Object<SelectorLabel>?,
    val selector: @Contextual Any?,
//    val selectorLabel: SelectorLabel?,
    val selectorPosition: String?,
    val selectorItemGap: Int?,
    val selectorButtonGap: Int?
)