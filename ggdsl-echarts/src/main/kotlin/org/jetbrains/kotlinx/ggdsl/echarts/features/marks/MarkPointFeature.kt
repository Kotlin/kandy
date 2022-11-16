package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

public class MarkPointFeature(
    public var points: List<MarkPoint>? = null
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("MARK_POINT_FEATURE")
    }
}

public class MarkPoint private constructor(
    internal val name: String? = null,
    internal val type: MarkType? = null,
    internal val coord: Pair<Number, Number>? = null,
    internal val x: String? = null,
    internal val y: String? = null,
    internal val valueMP: String? = null,
) {
    public constructor(point: Pair<String, MarkType>, value: String? = null) : this(
        point.first,
        type = point.second,
        valueMP = value
    )

    public constructor(type: MarkType, value: String? = null) : this(type = type, valueMP = value)

    public constructor(name: String? = null, coord: Pair<Number, Number>, value: String? = null) : this(
        name = name,
        coord = coord,
        valueMP = value
    )

    public constructor(name: String, x: Number, y: Number, value: String? = null) : this(
        name,
        x = x.toString(),
        y = y.toString(),
        valueMP = value
    )

    public constructor(x: Number, y: Number, value: String? = null) : this(
        x = x.toString(),
        y = y.toString(),
        valueMP = value
    )

    public constructor(name: String, x: String, y: Number, value: String? = null) : this(
        name,
        x = x,
        y = y.toString(),
        valueMP = value
    )

    public constructor(x: String, y: Number, value: String? = null) : this(x = x, y = y.toString(), valueMP = value)

    public constructor(name: String, x: Number, y: String, value: String? = null) : this(
        name,
        x = x.toString(),
        y = y,
        valueMP = value
    )

    public constructor(x: Number, y: String, value: String? = null) : this(x = x.toString(), y = y, valueMP = value)

    public constructor(name: String, x: String, y: String, value: String? = null) : this(
        name,
        x = x,
        y = y,
        valueMP = value
    )

    public constructor(x: String, y: String, value: String? = null) : this(x = x, y = y, valueMP = value)
}