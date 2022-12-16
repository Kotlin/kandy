/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.pairOf
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.singleOf

/**
 * Line type.
 *
 * @property SOLID
 * @property DASHED
 * @property DOTTED
 *
 * @property px
 * @property pairPxOf
 */
public class LineType private constructor(internal val value: StringNumberArray) {
    public companion object {
        public val SOLID: LineType = LineType(StringValue("solid"))
        public val DASHED: LineType = LineType(StringValue("dashed"))
        public val DOTTED: LineType = LineType(StringValue("dotted"))

        public fun px(px: Pixel): LineType = LineType(singleOf(px))

        public fun pairPxOf(pair: Pair<Pixel, Pixel>): LineType = LineType(pairOf(pair.first, pair.second))
        public fun pairPxOf(first: Pixel, second: Pixel): LineType = LineType(pairOf(first, second))
    }
}