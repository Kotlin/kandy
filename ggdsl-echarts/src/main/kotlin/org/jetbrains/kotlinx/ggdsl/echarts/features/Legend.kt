package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.ggdsl.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.EchartsLegend

public inline fun EChartsLayout.legend(crossinline block: Legend.() -> Unit) {
    this.legend = Legend().apply(block)
}

public enum class LegendAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right")
}

public enum class LegendType(public val type: String) {
    PLAIN("plain"), SCROLL("scroll")
}

public enum class Orient(public val type: String) {
    HORIZONTAL("horizontal"), VERTICAL("vertical")
}

@PlotDslMarker
public class Legend(
    public var type: LegendType? = null,
    public var left: SizeUnit? = null,
    public var top: SizeUnit? = null,
    public var right: SizeUnit? = null,
    public var bottom: SizeUnit? = null,
    public var width: SizeUnit? = null,
    public var height: SizeUnit? = null,
    public var orient: Orient? = null,
    public var formatter: String? = null,
) {

    internal fun isEmpty(): Boolean =
        type == null && left == null && top == null && right == null && bottom == null && width == null
            && height == null && orient == null && formatter == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toEchartsLegend(): EchartsLegend =
        EchartsLegend(
            type = type?.type, left = left, top = top, right = right, bottom = bottom,
            width = width, height = height, orient = orient?.type, formatter = formatter
        )
}