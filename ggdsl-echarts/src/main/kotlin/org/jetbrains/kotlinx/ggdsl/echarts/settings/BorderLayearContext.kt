/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.dsl.internal.BindingContext
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderColorAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderRadiusAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderTypeAes
import org.jetbrains.kotlinx.ggdsl.echarts.aes.BorderWidthAes
import org.jetbrains.kotlinx.ggdsl.echarts.layers.BarContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.layers.PointContextImmutable
import org.jetbrains.kotlinx.ggdsl.util.color.Color

/**
 * Adds [border][BorderLayerContext] settings to [bars][org.jetbrains.kotlinx.ggdsl.echarts.layers.bars].
 */
public fun BarContextImmutable.border(block: BorderLayerContext.() -> Unit) {
    BorderLayerContext(this).apply(block)
}

/**
 * Adds [border][BorderLayerContext] settings to [points][org.jetbrains.kotlinx.ggdsl.echarts.layers.points].
 */
public fun PointContextImmutable.border(block: BorderLayerContext.() -> Unit) {
    BorderLayerContext(this).apply(block)
}

/**
 * Border context with aesthetic attribute properties.
 *
 * @property color border [color][Color].
 * @property width border width. By default `0`.
 * @property type border [type][LineType]. By default `solid`.
 * @property radius border radius. By default `0`.
 *
 * @see Color
 * @see LineType
 */
public class BorderLayerContext(context: BindingContext) {
    public val color: BorderColorAes = BorderColorAes(context)
    public val width: BorderWidthAes = BorderWidthAes(context)
    public val type: BorderTypeAes = BorderTypeAes(context)
    public val radius: BorderRadiusAes = BorderRadiusAes(context)
}
