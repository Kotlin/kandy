/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.layers.context

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerCollectorContext
import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.ir.geom.Geom
import org.jetbrains.kotlinx.kandy.letsplot.layers.geom.PATH

/**
 * Context class for managing path layers.
 *
 * This class provides the context in which path layers can be configured.
 * It inherits from [LayerContext] and implements the [LineInterface].
 *
 * @param parent the parent context for the layer.
 */
public open class PathContext(parent: LayerCollectorContext) : LayerContext(parent), LineInterface {

    /**
     * Gets the Geom object specific to **path** layers.
     *
     * @return the [Geom] object for **path**.
     */
    override val geom: Geom
        get() = PATH
}
