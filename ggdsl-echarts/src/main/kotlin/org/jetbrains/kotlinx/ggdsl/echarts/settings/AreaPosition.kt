package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.singleOf

public class AreaPosition private constructor(
    internal val position: StringNumberArray
) {
    public companion object {
        public val AUTO: AreaPosition = AreaPosition(StringValue("auto"))
        public val START: AreaPosition = AreaPosition(StringValue("start"))
        public val END: AreaPosition = AreaPosition(StringValue("end"))
        public fun number(number: Int): AreaPosition = AreaPosition(singleOf(number))
        public fun number(number: Double): AreaPosition = AreaPosition(singleOf(number))
    }
}