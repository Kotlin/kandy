package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

@PlotDslMarker
public class MarkAreaContext(
    public var areas: List<MarkArea>? = null
) {
    internal fun toMarkAreaFeature(): MarkAreaFeature =
        MarkAreaFeature(areas)
}

internal class MarkAreaFeature(
    public var areas: List<MarkArea>? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    companion object {
        val FEATURE_NAME: FeatureName = FeatureName("MARK_AREA_FEATURE")
    }
}

public class MarkArea constructor(
    internal val name: String,
    internal val point1: MarkPoint,
    internal val point2: MarkPoint
)