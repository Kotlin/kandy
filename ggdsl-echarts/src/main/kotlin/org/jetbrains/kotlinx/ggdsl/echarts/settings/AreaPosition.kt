package org.jetbrains.kotlinx.ggdsl.echarts.settings

public class AreaPosition private constructor(
    internal val position: String? = null,
    internal val number: Int? = null,
) {
    public constructor(value: Int) : this(number = value)

    public companion object {
        public val AUTO: AreaPosition = AreaPosition("auto")
        public val START: AreaPosition = AreaPosition("start")
        public val END: AreaPosition = AreaPosition("end")
    }
}