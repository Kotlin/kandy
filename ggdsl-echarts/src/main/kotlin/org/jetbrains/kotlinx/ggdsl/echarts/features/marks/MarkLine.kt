/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

@PlotDslMarker
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

public class MarkLine private constructor(
    internal val nameML: String,
    internal val typeML: MarkType? = null,
    internal val xAxis: Double? = null,
    internal val yAxis: Double? = null,
    internal val point1: MarkPoint? = null,
    internal val point2: MarkPoint? = null
) {
    public constructor(name: String, type: MarkType) : this(nameML = name, typeML = type)

    public constructor(name: String, point1: MarkPoint, point2: MarkPoint) : this(
        nameML = name,
        point1 = point1,
        point2 = point2
    )

    public companion object {
        public fun horizontal(name: String, yAxis: Number): MarkLine = MarkLine(nameML = name, xAxis = yAxis.toDouble())
        public fun vertical(name: String, xAxis: Number): MarkLine = MarkLine(nameML = name, xAxis = xAxis.toDouble())


    }
}