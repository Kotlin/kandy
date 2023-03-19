/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.scale.guide

import org.jetbrains.kotlinx.ggdsl.util.context.SelfInvocationContext


public data class Legend internal constructor(
    public var name: String? = null,
    public var show: Boolean? = null,
    public var calculable: Boolean? = null
) : SelfInvocationContext
