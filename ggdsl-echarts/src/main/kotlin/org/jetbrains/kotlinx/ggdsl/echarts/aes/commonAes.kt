/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.aes

import org.jetbrains.kotlinx.ggdsl.dsl.NonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalableNonPositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.ScalablePositionalAes
import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerPlotContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.AesName
import org.jetbrains.kotlinx.ggdsl.util.color.Color

/**
 * Provides aesthetic for mapping to the `x-axis`.
 *
 * @see ScalablePositionalAes
 * @see XAes
 */
public val LayerPlotContext.x: XAes
    get() = XAes(this)

/**
 * Provides aesthetic for mapping to the `y-axis`.
 *
 * @see ScalablePositionalAes
 * @see YAes
 */
public val LayerPlotContext.y: YAes
    get() = YAes(this)


internal val X: AesName = AesName("x")

/**
 * Aesthetic attribute for `x-axis`.
 *
 * @see ScalablePositionalAes
 */
public data class XAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = X
}

internal val Y: AesName = AesName("y")

/**
 * Aesthetic attribute for `y-axis`.
 *
 * @see ScalablePositionalAes
 */
public data class YAes(override val context: BindingContext) : ScalablePositionalAes {
    override val name: AesName = Y
}

internal val NAME: AesName = AesName("name")


/**
 * Aesthetic attribute for name of layers.
 *
 * @see NonPositionalAes
 */
public data class NameAes(override val context: BindingContext) : NonPositionalAes<String> {
    override val name: AesName = NAME
}

internal val SIZE: AesName = AesName("size")


/**
 * Aesthetic attribute for size.
 *
 * @see ScalableNonPositionalAes
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable
 */
public data class SizeAes(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: AesName = SIZE
}

internal val COLOR: AesName = AesName("color")

/**
 * Aesthetic attribute for [color][Color].
 *
 * @see ScalableNonPositionalAes
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable
 */
public data class ColorAes(override val context: BindingContext) : ScalableNonPositionalAes<Color> {
    override val name: AesName = COLOR
}

internal val ALPHA: AesName = AesName("line_alpha")

/**
 * Aesthetic attribute for opacity.
 *
 * @see ScalableNonPositionalAes
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
 * @see org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable
 */
public data class AlphaAes(override val context: BindingContext) : ScalableNonPositionalAes<Double> {
    override val name: AesName = ALPHA
}
