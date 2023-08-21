/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.translator

import org.jetbrains.kotlinx.kandy.ir.scale.FreeScale
import org.jetbrains.kotlinx.kandy.ir.scale.PositionalFreeScale
import org.jetbrains.kotlinx.kandy.letsplot.internal.LetsPlotPositionalMappingParameters
import org.jetbrains.letsPlot.intern.Feature
import kotlin.reflect.typeOf

internal fun FreeScale.wrap(featureBuffer: MutableList<Feature>) {
    when (this) {
        is PositionalFreeScale<*> -> featureBuffer.add(
            parameters.scale.wrap(
                aes, typeOf<Any>(),
                (parameters as? LetsPlotPositionalMappingParameters<*>)?.axis,
                false
            )
        )

        else -> TODO()
    }
}
