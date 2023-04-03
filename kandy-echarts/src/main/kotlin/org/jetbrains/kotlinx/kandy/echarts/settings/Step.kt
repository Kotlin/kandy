/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.settings

/**
 * Step line.
 * Stepped line turning point settings.
 *
 * @property TRUE
 * @property FALSE
 * @property START
 * @property MIDDLE
 * @property END
 */
public enum class Step(internal val type: String) {
    TRUE("true"), FALSE("false"), START("start"), MIDDLE("middle"), END("end")
}