/*
* Copyright 2020-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
*/

package org.jetbrains.kotlinx.kandy.letsplot

import org.jetbrains.kotlinx.kandy.dsl.internal.LayerContext
import org.jetbrains.kotlinx.kandy.letsplot.feature.Reversed

/**
 * Whether to reverse axes.
 */
public var LayerContext.reversed: Boolean
    get() = true
    set(value) {
        features[Reversed.FEATURE_NAME] = Reversed(value)
    }
