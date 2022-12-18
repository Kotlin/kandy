/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.translator.option.series.settings

import kotlinx.serialization.Serializable

@Serializable
internal data class UniversalTransition(
    val enabled: Boolean? = null,
    val seriesKey: String? = null,
    val divideShape: String? = null,
    val delay: String? = null // TODO function
)