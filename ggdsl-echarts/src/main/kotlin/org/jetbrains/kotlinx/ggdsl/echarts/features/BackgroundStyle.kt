/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.PlotDslMarker
import org.jetbrains.kotlinx.ggdsl.echarts.aes.*
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.settings.LineType


/**
 * Sets background style for [bars][org.jetbrains.kotlinx.ggdsl.echarts.layers.bars].
 *
 * - [color][BackgroundStyle.color] - background [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * - [borderColor][BackgroundStyle.borderColor] -
 * background border [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * - [borderWidth][BackgroundStyle.borderWidth] - background border width.
 * By default `0`.
 * - [borderType][BackgroundStyle.borderType] - border [type][LineType].
 * By default `solid`.
 * - [borderRadius][BackgroundStyle.borderRadius] - background border radius.
 * By default `0`.
 * - [shadowBlur][BackgroundStyle.shadowBlur] - background shadow blur.
 * - [shadowColor][BackgroundStyle.shadowColor] -
 * background shadow [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * - [alpha][BackgroundStyle.alpha] - background opacity.
 *
 * ```kotlin
 * plot {
 *     bars {
 *         background {
 *             color(Color.GREY)
 *             borderColor(Color.BLACK)
 *             borderWidth(1.0)
 *             borderType(LineType.DASHED)
 *             borderRadius(1.3)
 *             shadowBlur(10.0)
 *             shadowColor(Color.GREEN)
 *             alpha(0.7)
 *         }
 *     }
 * }
 * ```
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.bars
 */
public inline fun BarContextImmutable.background(crossinline block: BackgroundStyle.() -> Unit) {
    BackgroundStyle(this).apply(block)
}

/**
 * Background style settings. All properties of this class are aesthetics.
 *
 * @property color background [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * @property borderColor background border [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * @property borderWidth background border width. By default `0`.
 * @property borderType border [type][LineType]. By default `solid`.
 * @property borderRadius background border radius. By default `0`.
 * @property shadowBlur background shadow blur.
 * @property shadowColor background shadow [color][org.jetbrains.kotlinx.ggdsl.util.color.Color].
 * @property alpha background opacity.
 *
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.bars
 */
@PlotDslMarker
public class BackgroundStyle(context: BindingContext) {
    public val color: BackgroundColorAes = BackgroundColorAes(context)
    public val borderColor: BackgroundBorderColorAes = BackgroundBorderColorAes(context)
    public val borderWidth: BackgroundBorderWidthAes = BackgroundBorderWidthAes(context)
    public val borderType: BackgroundBorderTypeAes = BackgroundBorderTypeAes(context)
    public val borderRadius: BackgroundBorderRadiusAes = BackgroundBorderRadiusAes(context)
    public val shadowBlur: BackgroundShadowBlurAes = BackgroundShadowBlurAes(context)
    public val shadowColor: BackgroundShadowColorAes = BackgroundShadowColorAes(context)
    public val alpha: BackgroundAlphaAes = BackgroundAlphaAes(context)
}