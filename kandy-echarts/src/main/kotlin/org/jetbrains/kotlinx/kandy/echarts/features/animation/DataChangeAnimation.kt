/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.features.animation

import org.jetbrains.kotlinx.kandy.dsl.internal.dataframe.NamedData
import org.jetbrains.kotlinx.kandy.ir.Plot


public data class DataChangeAnimation internal constructor(
    val plot: Plot,
    val interval: Int,
    val dataChange: NamedData.() -> Unit
)

public fun Plot.withDataChangeAnimation(
    interval: Int,
    dataChange: NamedData.() -> Unit
): DataChangeAnimation = DataChangeAnimation(this, interval, dataChange)

