package org.jetbrains.kotlinx.ggdsl.letsplot

import org.jetbrains.kotlinx.ggdsl.dsl.LayerContext
import org.jetbrains.kotlinx.ggdsl.dsl.PlotContext
import org.jetbrains.kotlinx.ggdsl.ir.aes.NonScalablePositionalAes

// todo remove???
val GROUP = NonScalablePositionalAes("group")

val LayerContext.group: NonScalablePositionalAes
    get() = GROUP
