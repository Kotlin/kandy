/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.builders

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCreatorScope
import org.jetbrains.kotlinx.kandy.ir.aes.Aes
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.internal.X
import org.jetbrains.kotlinx.kandy.letsplot.internal.Y
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.BarsBuilderInterface
import org.jetbrains.kotlinx.kandy.letsplot.layers.builders.interfaces.LayerWithBorderLineBuilder
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.BAR

/**
 * Builder class for managing Bars layers.
 *
 * This class provides the context in which Bars layers can be configured.
 * It inherits from [LayerWithBorderLineBuilder] and implements the [BarsBuilderInterface].
 *
 * @param parent the parent [LayerCreatorScope] for the layer.
 */
@Suppress("INVISIBLE_MEMBER", "CANNOT_OVERRIDE_INVISIBLE_MEMBER")
public open class BarsBuilder @PublishedApi internal constructor(
    parent: LayerCreatorScope,
    datasetIndex: Int = parent.datasetIndex
) : LayerWithBorderLineBuilder(parent, datasetIndex), BarsBuilderInterface {
    override val geom: Geom
        get() = BAR

    override val requiredAes: Set<Aes>
        get() = setOf(X, Y)
}
