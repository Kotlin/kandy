package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

@PlotDslMarker
public class MarkLineFeature(
    public var lines: List<MarkLine>? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("MARK_LINE_FEATURE")
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