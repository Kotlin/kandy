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
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.aes.*
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.ABLineBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.AB_LINE

/**
 * Context class for managing ABLine layers.
 *
 * This class provides the context in which ABLine layers can be configured.
 * It inherits from [LayerContext] and implements the [ABLineBuilderInterface].
 *
 * @param parent the parent context for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class ABLineBuilder @PublishedApi internal constructor(parent: LayerCreatorScope) :
    LayerBuilderImpl(parent), ABLineBuilderInterface {
    internal override val geom: Geom
        get() = AB_LINE
    internal override val requiredAes: Set<Aes>
        get() = setOf(SLOPE, INTERCEPT)
}
