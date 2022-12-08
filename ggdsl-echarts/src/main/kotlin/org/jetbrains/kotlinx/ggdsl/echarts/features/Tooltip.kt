package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout

public inline fun EChartsLayout.tooltip(crossinline block: Tooltip.() -> Unit) {
    this.tooltip = Tooltip().apply(block)
}

public enum class Trigger(public val type: String) {
    ITEM("item"), AXIS("axis"), NONE("none")
}

@PlotDslMarker
public class Tooltip(
    public var trigger: Trigger? = null,
    public var formatter: String? = null
) {
    internal fun isEmpty(): Boolean =
        trigger == null && formatter == null

    internal fun isNotEmpty(): Boolean = !isEmpty()
}
