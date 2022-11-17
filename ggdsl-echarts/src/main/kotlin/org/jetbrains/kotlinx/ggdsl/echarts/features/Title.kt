package org.jetbrains.kotlinx.ggdsl.echarts.features

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.ggdsl.dsl.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.PlotContext
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Color
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.PlotFeature

@Serializable
public enum class TextAlign(public val align: String) {
    AUTO("auto"), LEFT("left"), RIGHT("right"), CENTER("center")
}

public inline fun PlotContext.title(crossinline block: TitleFeature.() -> Unit) {
    features[TitleFeature.FEATURE_NAME] = TitleFeature().apply(block)
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
