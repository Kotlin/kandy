package org.jetbrains.kotlinx.ggdsl.echarts.features.marks

import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

public class MarkPointFeature(
    public var points: List<MarkPoint>? = null
//    public var enable: Boolean = true,
//    public var threshold: Int = 2000,
//    public var duration: Int = 1000,
//    public var easing: AnimationEasing = AnimationEasing.CUBIC_OUT,
//    public var delay: Int = 0
) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("MARK_POINT_FEATURE")
    }
}

public class MarkPoint private constructor(
    internal val name: String? = null,
    internal val type: String? = null,
    internal val coord: Pair<Double, Double>? = null,
    internal val x: String? = null,
    internal val y: String? = null,
    internal val valueMP: String? = null,
) {
    public constructor(point: Pair<String, String>, value: String? = null) : this(
        point.first,
        type = point.second,
        valueMP = value
    )

    public constructor(type: String, value: String? = null) : this(type = type, valueMP = value)

    public constructor(name: String? = null, coord: Pair<Double, Double>, value: String? = null) : this(
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