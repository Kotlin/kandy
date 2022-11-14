/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerCollectorContext
import org.jetbrains.kotlinx.ggdsl.dsl.contexts.LayerContext
import org.jetbrains.kotlinx.ggdsl.echarts.NameAes

public sealed class EchartsLayerContext(parent: LayerCollectorContext) : LayerContext(parent) {
    public val name: NameAes = NameAes(this)
}