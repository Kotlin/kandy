/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.features.label

import org.jetbrains.kotlinx.ggdsl.echarts.settings.Percentage
import org.jetbrains.kotlinx.ggdsl.echarts.settings.Pixel
import org.jetbrains.kotlinx.ggdsl.echarts.settings.SizeUnit
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.pairOf


/**
 * Label position settings.
 *
 * @see SizeUnit
 */
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
        /**
         * `top` label position.
         */
        public val TOP: LabelPosition = LabelPosition("top")

        /**
         * `top` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun top(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("top", distance, rotate)

        /**
         * `left` label position.
         */
        public val LEFT: LabelPosition = LabelPosition("left")

        /**
         * `left` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun left(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("left", distance, rotate)

        /**
         * `right` label position.
         */
        public val RIGHT: LabelPosition = LabelPosition("right")

        /**
         * `right` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun right(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("right", distance, rotate)

        /**
         * `bottom` label position.
         */
        public val BOTTOM: LabelPosition = LabelPosition("bottom")

        /**
         * `bottom` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun bottom(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("bottom", distance, rotate)

        /**
         * `inside` label position.
         */
        public val INSIDE: LabelPosition = LabelPosition("inside")

        /**
         * `inside` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun inside(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("inside", distance, rotate)

        /**
         * `insideLeft` label position.
         */
        public val INSIDE_LEFT: LabelPosition = LabelPosition("insideLeft")

        /**
         * `insideLeft` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideLeft(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideLeft", distance, rotate)

        /**
         * `insideRight` label position.
         */
        public val INSIDE_RIGHT: LabelPosition = LabelPosition("insideRight")

        /**
         * `insideRight` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideRight(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideRight", distance, rotate)

        /**
         * `insideTop` label position.
         */
        public val INSIDE_TOP: LabelPosition = LabelPosition("insideTop")

        /**
         * `insideTop` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideTop(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideTop", distance, rotate)

        /**
         * `insideBottom` label position.
         */
        public val INSIDE_BOTTOM: LabelPosition = LabelPosition("insideBottom")

        /**
         * `insideBottom` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideBottom(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideBottom", distance, rotate)

        /**
         * `insideTopLeft` label position.
         */
        public val INSIDE_TOP_LEFT: LabelPosition = LabelPosition("insideTopLeft")

        /**
         * `insideTopLeft` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideTopLeft(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideTopLeft", distance, rotate)

        /**
         * `insideBottomLeft` label position.
         */
        public val INSIDE_BOTTOM_LEFT: LabelPosition = LabelPosition("insideBottomLeft")

        /**
         * `insideBottomLeft` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideBottomLeft(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideBottomLeft", distance, rotate)

        /**
         * `insideTopRight` label position.
         */
        public val INSIDE_TOP_RIGHT: LabelPosition = LabelPosition("insideTopRight")

        /**
         * `insideTopRight` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideTopRight(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideTopRight", distance, rotate)

        /**
         * `insideBottomRight` label position.
         */
        public val INSIDE_BOTTOM_RIGHT: LabelPosition = LabelPosition("insideBottomRight")

        /**
         * `insideBottomRight` label position with [distance] and [rotate]
         *
         * @param distance distance to the host graphic element.
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun insideBottomRight(distance: Int? = null, rotate: Int? = null): LabelPosition =
            LabelPosition("insideBottomRight", distance, rotate)

        /**
         * Represents position of label relative to a top-left corner of bounding box in absolute pixel values.
         *
         * @param pair pair of absolute pixel values
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun fromPx(pair: Pair<Pixel, Pixel>, rotate: Int? = null): LabelPosition = LabelPosition(pair, rotate)

        /**
         * Represents position of label relative to a top-left corner of bounding box in absolute pixel values.
         *
         * @param first pixel value along the x-axis
         * @param second pixel value along the y-axis
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun fromPx(first: Pixel, second: Pixel, rotate: Int? = null): LabelPosition =
            LabelPosition(first to second, rotate)

        /**
         * Represents position of label relative to a top-left corner of bounding box in relative percentage.
         *
         * @param pair pair of relative percentages
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun fromPct(pair: Pair<Percentage, Percentage>, rotate: Int? = null): LabelPosition =
            LabelPosition(pair, rotate)

        /**
         * Represents position of label relative to a top-left corner of bounding box in relative percentage.
         *
         * @param first relative percentage along the x-axis
         * @param second relative percentage along the y-axis
         * @param rotate rotate label, from `-90` degree to `90`, positive value represents rotate anti-clockwise.
         */
        public fun fromPct(first: Percentage, second: Percentage, rotate: Int? = null): LabelPosition =
            LabelPosition(first to second, rotate)
    }
}