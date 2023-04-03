/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.translator.option

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
internal data class Polar(
    val id: String?,
    val zlevel: Int?,
    val z: Int?,
    val center: List<@Contextual Any>?, // ['50%', '50%']
    val radius: @Contextual Any?, // number string array
//    val tooltip: Tooltip?
)
