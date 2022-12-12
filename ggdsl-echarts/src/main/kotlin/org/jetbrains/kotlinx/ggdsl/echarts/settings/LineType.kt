package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.pairOf
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.singleOf

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