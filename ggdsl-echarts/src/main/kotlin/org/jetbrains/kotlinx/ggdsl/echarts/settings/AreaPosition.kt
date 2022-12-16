/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.settings

import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringNumberArray
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.StringValue
import org.jetbrains.kotlinx.ggdsl.echarts.translator.option.util.singleOf

/**
 * Position of area.
 *
 * By default, the area between axis line and data will be filled.
 * This config enables you to fill the area from data to the max or min of the axis data or a specified value.
 *
 * @property AUTO to fill between axis line and data.
 * @property START to fill between min axis value and data.
 * @property END to fill between max axis value and data.
 * @property number to fill between specified value and data.
 */
public class AreaPosition private constructor(
    internal val position: StringNumberArray
) {
    public companion object {
        /**
         * to fill between axis line and data.
         */
        public val AUTO: AreaPosition = AreaPosition(StringValue("auto"))

        /**
         * to fill between min axis value and data.
         */
        public val START: AreaPosition = AreaPosition(StringValue("start"))

        /**
         * to fill between max axis value and data.
         */
        public val END: AreaPosition = AreaPosition(StringValue("end"))

        /**
         * to fill between specified value and data.
         */
        public fun number(number: Int): AreaPosition = AreaPosition(singleOf(number))

        /**
         * to fill between specified value and data.
         */
        public fun number(number: Double): AreaPosition = AreaPosition(singleOf(number))
    }
}