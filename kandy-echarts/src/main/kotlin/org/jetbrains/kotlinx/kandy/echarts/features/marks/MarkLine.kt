/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features.marks

// import org.jetbrains.kotlinx.kandy.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.kandy.ir.feature.FeatureName
import org.jetbrains.kotlinx.kandy.ir.feature.LayerFeature

/**
 * The mark line settings for layers.
 *
 * @property lines list of [mark line][MarkLine]
 *
 * @see MarkLine
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.line
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.area
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.bars
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.points
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.pie
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.boxplot
 * @see org.jetbrains.kotlinx.kandy.echarts.layers.candlestick
 */
/*@PlotDslMarker*/
public class MarkLineContext(
    public var lines: List<MarkLine>? = null
) {
    internal fun toMarkLineFeature(): MarkLineFeature =
        MarkLineFeature(lines)
}

internal class MarkLineFeature(
    var lines: List<MarkLine>? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("MARK_LINE_FEATURE")
    }
}

/**
 * The mark line. Describes the line mark on a plot.
 *
 * @see MarkType
 * @see MarkType
 */
public class MarkLine private constructor(
    internal val nameML: String,
    internal val typeML: MarkType? = null,
    internal val xAxis: Double? = null,
    internal val yAxis: Double? = null,
    internal val point1: MarkPoint? = null,
    internal val point2: MarkPoint? = null
) {
    /**
     * Returns the [mark line][MarkLine] of a selected [type] with a [name].
     *
     * [MarkType]:
     * - MAX
     * - MIN
     * - AVERAGE
     */
    public constructor(name: String, type: MarkType) : this(nameML = name, typeML = type)

    /**
     * Returns the [mark line][MarkLine] with [name]. The line is drawn between the [point1] and [point2].
     */
    public constructor(name: String, point1: MarkPoint, point2: MarkPoint) : this(
        nameML = name,
        point1 = point1,
        point2 = point2
    )

    public companion object {
        /**
         * Returns a horizontal line along a point on the y-axis.
         */
        public fun horizontal(name: String, yAxis: Number): MarkLine = MarkLine(nameML = name, xAxis = yAxis.toDouble())

        /**
         * Returns a horizontal line along a point on the x-axis.
         */
        public fun vertical(name: String, xAxis: Number): MarkLine = MarkLine(nameML = name, xAxis = xAxis.toDouble())
    }
}