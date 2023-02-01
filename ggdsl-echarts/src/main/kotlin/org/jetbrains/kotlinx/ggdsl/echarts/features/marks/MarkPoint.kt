/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

// import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature


/**
 * The mark point settings for layers.
 *
 * @property points list of [mark point][MarkPoint]
 *
 * @see MarkPoint
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.line
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.area
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.bars
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.points
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.pie
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.boxplot
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.candlestick
 */
/*@PlotDslMarker*/
public class MarkPointContext(
    public var points: List<MarkPoint>? = null
) {
    internal fun toMarkPointFeature(): MarkPointFeature =
        MarkPointFeature(points)
}

internal class MarkPointFeature(
    var points: List<MarkPoint>? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("MARK_POINT_FEATURE")
    }
}

/**
 * The mark point. Arbitrary point on the plot.
 */
public class MarkPoint private constructor(
    internal val name: String? = null,
    internal val type: MarkType? = null,
    internal val coord: Pair<Number, Number>? = null,
    internal val x: String? = null,
    internal val y: String? = null,
    internal val valueMP: String? = null,
) {
    /**
     * Returns the [mark point][MarkPoint] of a selected [type] with a [name] and [value] assigned to that point.
     *
     * [MarkType]:
     * - MAX
     * - MIN
     * - AVERAGE
     */
    public constructor(name: String, type: MarkType, value: String? = null) : this(
        name = name,
        type = type,
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] of a selected [type] with [value] assigned to that point and without a name.
     */
    public constructor(type: MarkType, value: String? = null) : this(type = type, valueMP = value)

    /**
     * Returns the [mark point][MarkPoint] by [coord] with [value] assigned to that point.
     */
    public constructor(name: String? = null, coord: Pair<Number, Number>, value: String? = null) : this(
        name = name,
        coord = coord,
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(name: String, x: Number, y: Number, value: String? = null) : this(
        name,
        x = x.toString(),
        y = y.toString(),
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(x: Number, y: Number, value: String? = null) : this(
        x = x.toString(),
        y = y.toString(),
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(name: String, x: String, y: Number, value: String? = null) : this(
        name,
        x = x,
        y = y.toString(),
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(x: String, y: Number, value: String? = null) : this(x = x, y = y.toString(), valueMP = value)

    public constructor(name: String, x: Number, y: String, value: String? = null) : this(
        name,
        x = x.toString(),
        y = y,
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(x: Number, y: String, value: String? = null) : this(x = x.toString(), y = y, valueMP = value)

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(name: String, x: String, y: String, value: String? = null) : this(
        name,
        x = x,
        y = y,
        valueMP = value
    )

    /**
     * Returns the [mark point][MarkPoint] by [x], [y] with [value] assigned to that point.
     */
    public constructor(x: String, y: String, value: String? = null) : this(x = x, y = y, valueMP = value)
}