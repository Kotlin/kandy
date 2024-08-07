/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.INTERCEPT
import org.jetbrains.kotlinx.kandy.letsplot.internal.SLOPE
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.ABLineBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AB_LINE

/**
 * Builder class for managing ABLine layers.
 *
 * This class provides the context in which ABLine layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [ABLineBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class ABLineBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) :
    LayerBuilderImpl(parent, datasetIndex), ABLineBuilderInterface {
    override val geom: Geom
        get() = AB_LINE
    override val requiredAes: Set<Aes>
        get() = setOf(SLOPE, INTERCEPT)
}
