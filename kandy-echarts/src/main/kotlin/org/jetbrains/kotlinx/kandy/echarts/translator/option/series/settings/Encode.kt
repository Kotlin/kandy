/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class Encode(val x: String? = null, val y: List<String>? = null) {
    fun isEmpty(): Boolean = x == null && y == null

    fun isNotEmpty(): Boolean = !this.isEmpty()
}
