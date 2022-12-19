/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings.EchartsTooltip

/**
 * Configures global tooltip.
 *
 * - [trigger][Tooltip.trigger] - [type][Trigger] of triggering
 * - [formatter][Tooltip.formatter] - the content formatter of tooltip's floating layer
 *
 * ```kotlin
 * plot {
 *  layout {
 *      tooltip {
 *          trigger = Trigger.AXIS
 *          formatter = "layer {a}}
 *      }
 *  }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.layout
 * @see EChartsLayout
 */
public inline fun EChartsLayout.tooltip(crossinline block: Tooltip.() -> Unit) {
    this.tooltip = Tooltip().apply(block)
}

/**
 * Type of triggering.
 *
 * @property ITEM triggered by data item,
 * which is mainly used for charts that don't have a category axis like scatter charts or pie charts.
 * @property AXIS triggered by axes, which is mainly used for charts that have category axes, like bar charts or line charts.
 * @property NONE trigger nothing.
 *
 */
public enum class Trigger(public val type: String) {
    ITEM("item"), AXIS("axis"), NONE("none")
}

/**
 * Tooltip settings.
 *
 * @property trigger [type][Trigger] of triggering
 * @property formatter the content formatter of tooltip's floating layer
 *
 * @see Trigger
 */
@PlotDslMarker
public class Tooltip(
    public var trigger: Trigger? = null,
    public var formatter: String? = null
) {
    internal fun isEmpty(): Boolean =
        trigger == null && formatter == null

    internal fun isNotEmpty(): Boolean = !isEmpty()

    internal fun toEchartsTooltip(): EchartsTooltip =
        EchartsTooltip(
            trigger = trigger?.type,
            formatter = formatter
        )
}
