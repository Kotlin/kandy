package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderColorAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderRadiusAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderTypeAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderWidthAes
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.ir.feature.FeatureName
import org.jetbrains.kotlinx.ggdsl.ir.feature.LayerFeature

public fun BarContextImmutable.border(block: BorderFeature.() -> Unit) {
    BorderFeature(this).apply(block)
}

public class BorderFeature(context: BindingContext) : LayerFeature {
    override val featureName: FeatureName = FEATURE_NAME

    public val color: BorderColorAes = BorderColorAes(context)
    public val width: BorderWidthAes = BorderWidthAes(context)
    public val type: BorderTypeAes = BorderTypeAes(context)
    public val radius: BorderRadiusAes = BorderRadiusAes(context)

    public companion object {
        public val FEATURE_NAME: FeatureName = FeatureName("BAR_BORDER_FEATURE")
    }
}