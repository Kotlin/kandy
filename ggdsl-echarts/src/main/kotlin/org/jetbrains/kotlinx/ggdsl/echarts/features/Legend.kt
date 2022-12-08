package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout

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
    public var left: Int? = null,
    public var top: Int? = null,
    public var right: Int? = null,
    public var bottom: Int? = null,
    public var width: Int? = null,
    public var height: Int? = null,
    public var orient: Orient? = null,
    public var formatter: String? = null,
) {

    internal fun isEmpty(): Boolean =
        type == null && left == null && top == null && right == null && bottom == null && width == null
            && height == null && orient == null && formatter == null

    internal fun isNotEmpty(): Boolean = !isEmpty()
}