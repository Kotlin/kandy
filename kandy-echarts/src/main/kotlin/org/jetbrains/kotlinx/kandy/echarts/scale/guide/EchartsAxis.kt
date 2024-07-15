/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.echarts.scale.guide

import org.jetbrains.kotlinx.kandy.util.context.SelfInvocationContext

public data class Axis internal constructor(
    var name: String? = null,
    var show: Boolean? = null
) : SelfInvocationContext
