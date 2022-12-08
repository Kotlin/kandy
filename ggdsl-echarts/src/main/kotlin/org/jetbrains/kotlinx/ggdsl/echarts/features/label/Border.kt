package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType
import org.jetbrains.kotlinx.ggdsl.util.color.Color
import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext

@PlotDslMarker
public class Border(
    public var color: Color? = null,
    public var width: Double? = null,
    public var type: LineType? = null,
    public var radius: Double? = null
) : SelfInvocationContext {

    internal fun isEmpty(): Boolean =
        color == null && width == null && type == null && radius == null

    internal fun isNotEmpty(): Boolean = !isEmpty()
}