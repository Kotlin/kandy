package org.jetbrains.kotlinx.ggdsl.dataframe.letsplot.util.serialization

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import org.jetbrains.kotlinx.ggdsl.dataframe.util.serialization.dataframeModule
import org.jetbrains.kotlinx.ggdsl.letsplot.util.serialization.letsPlotModules
import org.jetbrains.kotlinx.ggdsl.util.serialization.commonModules

public val allModules: SerializersModule
    get() = letsPlotModules + commonModules + dataframeModule
