/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot.position

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext

/**
 * Position adjustment of this layer.
 *
 * @see [Position]
 */
public var LayerContext.position: Position
    get() = Position.Identity // todo
    set(pos) {
        features[Position.FEATURE_NAME] = pos
    }
