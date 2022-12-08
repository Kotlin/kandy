package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable

public inline fun BarContextImmutable.background(crossinline block: BackgroundStyle.() -> Unit) {
    BackgroundStyle(this).apply(block)
}

@PlotDslMarker
public class BackgroundStyle(context: BindingContext) {
    public val color: BackgroundColorAes = BackgroundColorAes(context)
    public val borderColor: BackgroundBorderColorAes = BackgroundBorderColorAes(context)
    public val borderWidth: BackgroundBorderWidthAes = BackgroundBorderWidthAes(context)
    public val borderType: BackgroundBorderTypeAes = BackgroundBorderTypeAes(context)
    public val borderRadius: BackgroundBorderRadiusAes = BackgroundBorderRadiusAes(context)
    public val shadowBlur: BackgroundShadowBlurAes = BackgroundShadowBlurAes(context)
    public val shadowColor: BackgroundShadowColorAes = BackgroundShadowColorAes(context)
}