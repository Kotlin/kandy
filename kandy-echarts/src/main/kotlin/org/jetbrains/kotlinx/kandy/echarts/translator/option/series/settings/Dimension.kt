/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class Dimension(
    val name: String? = null,
    val type: String? = null,
    val displayName: String? = null
)