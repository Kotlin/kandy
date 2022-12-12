package org.jetbrains.kotlinx.ggdsl.echarts.features

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.features.text.TextStyleFeature
import org.jetbrains.kotlinx.ggdsl.echarts.layers.EChartsLayout
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature
import org.jetbrains.kotlinx.ggdsl.util.color.Color

public inline fun EChartsLayout.title(crossinline block: TitleFeature.() -> Unit) {
    this.titleFeature = TitleFeature().apply(block)
}

@Serializable
public enum class TextAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right"), CENTER("center")
}

@PlotDslMarker
public class TitleFeature(
    public var text: String? = null,
    public var subtext: String? = null,
    public var align: TextAlign? = null,
    public var verticalAlign: TextAlign? = null,
    public var backgroundColor: Color? = null,
    public var borderColor: Color? = null,
    public var borderWidth: Int? = null
) : PlotFeature {
    override val featureName: FeatureName = FEATURE_NAME

    internal var textStyle: TextStyleFeature? = null

    internal var subtextStyle: TextStyleFeature? = null

    public fun textStyle(body: TextStyleFeature.() -> Unit) {
        textStyle = TextStyleFeature().apply(body)
    }

    public fun subtextStyle(body: TextStyleFeature.() -> Unit) {
        subtextStyle = TextStyleFeature().apply(body)
    }

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("TITLE_PLOT_FEATURE")
    }
}
