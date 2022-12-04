package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

public inline fun BarContextImmutable.background(crossinline block: BackgroundStyleFeature.() -> Unit) {
    BackgroundStyleFeature(this).apply(block)
}

public class BackgroundStyleFeature(context: BindingContext) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public val color: BackgroundColorAes = BackgroundColorAes(context)
    public val borderColor: BackgroundBorderColorAes = BackgroundBorderColorAes(context)
    public val borderWidth: BackgroundBorderWidthAes = BackgroundBorderWidthAes(context)
    public val borderType: BackgroundBorderTypeAes = BackgroundBorderTypeAes(context)
    public val borderRadius: BackgroundBorderRadiusAes = BackgroundBorderRadiusAes(context)
    public val shadowBlur: BackgroundShadowBlurAes = BackgroundShadowBlurAes(context)
    public val shadowColor: BackgroundShadowColorAes = BackgroundShadowColorAes(context)

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("BAR_BACKGROUND_STYLE_FEATURE")
    }
}