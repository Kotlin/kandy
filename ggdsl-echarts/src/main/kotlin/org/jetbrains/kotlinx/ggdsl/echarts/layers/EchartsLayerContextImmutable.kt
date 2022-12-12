/*
* Copyright 2020-2022 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.ggdsl.echarts.layers

import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerCollectorContextImmutable
import org.jetbrains.kotlinx.ggdsl.dsl.internal.LayerContextImmutable
import org.jetbrains.kotlinx.ggdsl.echarts.aes.NameAes

public sealed class EchartsLayerContextImmutable(parent: LayerCollectorContextImmutable) : LayerContextImmutable(parent) {
    public val name: NameAes = NameAes(this)
}