package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.pairOf

public class LabelPosition private constructor(
    private val name: String?,
    private val pos: Pair<SizeUnit, SizeUnit>?,
    internal val distance: Int?,
    internal val rotate: Int?
) {

    private constructor(name: String, distance: Int? = null, rotate: Int? = null) : this(
        name, null, distance, rotate
    )

    private constructor(pos: Pair<SizeUnit, SizeUnit>, rotate: Int? = null) : this(
        null, pos, null, rotate
    )

    internal fun getPosition(): StringNumberArray? =
        when {
            name != null -> StringValue(name)
            pos != null -> pairOf(pos.first, pos.second)
            else -> null
        }

    public companion object {
        public val TOP: LabelPosition = LabelPosition("top")
        public fun top(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("top", distance, rotate)

        public val LEFT: LabelPosition = LabelPosition("left")
        public fun left(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("left", distance, rotate)

        public val RIGHT: LabelPosition = LabelPosition("right")
        public fun right(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("right", distance, rotate)

        public val BOTTOM: LabelPosition = LabelPosition("bottom")
        public fun bottom(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("bottom", distance, rotate)

        public val INSIDE: LabelPosition = LabelPosition("inside")
        public fun inside(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("inside", distance, rotate)

        public val INSIDE_LEFT: LabelPosition = LabelPosition("insideLeft")
        public fun insideLeft(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideLeft", distance, rotate)

        public val INSIDE_RIGHT: LabelPosition = LabelPosition("insideRight")
        public fun insideRight(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideRight", distance, rotate)

        public val INSIDE_TOP: LabelPosition = LabelPosition("insideTop")
        public fun insideTop(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideTop", distance, rotate)

        public val INSIDE_BOTTOM: LabelPosition = LabelPosition("insideBottom")
        public fun insideBottom(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideBottom", distance, rotate)

        public val INSIDE_TOP_LEFT: LabelPosition = LabelPosition("insideTopLeft")
        public fun insideTopLeft(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideTopLeft", distance, rotate)

        public val INSIDE_BOTTOM_LEFT: LabelPosition = LabelPosition("insideBottomLeft")
        public fun insideBottomLeft(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideBottomLeft", distance, rotate)

        public val INSIDE_TOP_RIGHT: LabelPosition = LabelPosition("insideTopRight")
        public fun insideTopRight(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideTopRight", distance, rotate)

        public val INSIDE_BOTTOM_RIGHT: LabelPosition = LabelPosition("insideBottomRight")
        public fun insideBottomRight(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideBottomRight", distance, rotate)
    }
}