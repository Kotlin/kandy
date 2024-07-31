/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerBuilderImpl
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LineBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.LINE

/**
 * Builder class for managing line layers.
 *
 * This class provides the context in which line layers can be configured.
 * It inherits from [LayerBuilderImpl] and implements the [LineBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class LineBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerBuilderImpl(parent, datasetIndex), LineBuilderInterface {
    override val geom: Geom
        get() = LINE
    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}
